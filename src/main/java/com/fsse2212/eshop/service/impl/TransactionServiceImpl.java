package com.fsse2212.eshop.service.impl;

import com.fsse2212.eshop.data.cartitem.CartItemEntity;
import com.fsse2212.eshop.data.transaction.TransactionDetailData;
import com.fsse2212.eshop.data.transaction.TransactionEntity;
import com.fsse2212.eshop.data.transaction.TransactionStatusData;
import com.fsse2212.eshop.data.transactionproduct.TransactionProductEntity;
import com.fsse2212.eshop.data.user.FirebaseUserData;
import com.fsse2212.eshop.data.user.UserEntity;
import com.fsse2212.eshop.exception.*;
import com.fsse2212.eshop.repository.TransactionRepository;
import com.fsse2212.eshop.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final UserService userService;
    private final ProductService productService;
    private final TransactionRepository transactionRepository;
    private final CartItemService cartItemService;
    private final TransactionProductService transactionProductService;

    Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

    public TransactionServiceImpl(UserService userService,
                                  ProductService productService,
                                  TransactionRepository transactionRepository,
                                  CartItemService cartItemService,
                                  TransactionProductService transactionProductService) {
        this.userService = userService;
        this.productService = productService;
        this.transactionRepository = transactionRepository;
        this.cartItemService = cartItemService;
        this.transactionProductService = transactionProductService;
    }

    @Override
    public TransactionDetailData createTransaction(FirebaseUserData userData) {
        try {
            UserEntity loginUser = userService.getEntityByFirebaseUser(userData);
            if (loginUser == null) { // for 100% safe
                throw new NullUserException();
            }
            removeExpiredTransaction(loginUser);
            BigDecimal total = new BigDecimal(0);

            List<CartItemEntity> cartItemEntityList = cartItemService.getAllCartItem(loginUser);

            TransactionEntity transaction = transactionRepository.save(new TransactionEntity(loginUser));

            List<TransactionProductEntity> list = new ArrayList<>();
            for (CartItemEntity entity : cartItemEntityList) {
                list.add(new TransactionProductEntity(entity, transaction));
            }
            transaction.setTransactionProduct(transactionProductService.saveList(list));
            for (TransactionProductEntity entity : transaction.getTransactionProduct()) {
                total = total.add(entity.getPrice().multiply(BigDecimal.valueOf(entity.getQuantity())));
            }
            transaction.setTotal(total);
            return new TransactionDetailData(transactionRepository.save(transaction));
        } catch (NullUserException e) {
            logger.warn("Create Transaction: User Not Found!");
            throw e;
        }
    }

    @Override
    public TransactionDetailData getTransaction(FirebaseUserData userData, Integer tid) {
        try {
            UserEntity loginUser = userService.getEntityByFirebaseUser(userData);
            if (loginUser == null) { // for 100% safe
                throw new NullUserException();
            }
            TransactionEntity foundTransaction = getTransactionEntity(tid);
            if (!loginUser.getUid().equals(foundTransaction.getUser().getUid())) {
                throw new WrongUserException();
            }
            return new TransactionDetailData(foundTransaction);
        } catch (NullUserException e) {
            logger.warn("Get Transaction: User Not Found!");
            throw e;
        } catch (TransactionNotFoundException e){
            logger.warn("Get: Transaction Not Found!");
            throw e;
        } catch (WrongUserException e) {
            logger.warn("Get Transaction: Wrong User!");
            throw e;
        }
    }

    @Override
    public TransactionStatusData payment(FirebaseUserData userData, Integer tid) {
        try {
            UserEntity loginUser = userService.getEntityByFirebaseUser(userData);
            if (loginUser == null) { // for 100% safe
                throw new NullUserException();
            }
            TransactionEntity foundTransaction = getTransactionEntity(tid);
            if (!loginUser.getUid().equals(foundTransaction.getUser().getUid())) {
                throw new WrongUserException();
            }
            if (foundTransaction.getStatus() != Status.PREPARE) {
                throw new WrongStatusException();
            }
            // Check Stock
            for (TransactionProductEntity entity : foundTransaction.getTransactionProduct()) {
                if (entity.getQuantity() > productService.getProductEntity(entity.getPid()).getStockQuantity()) {
                    throw new OutOfStockException();
                }
            }
            for (TransactionProductEntity entity : foundTransaction.getTransactionProduct()) {
                productService.setProductQuantity(entity.getQuantity(), entity.getPid());
            }
            foundTransaction.setStatus(Status.PROCESSING);
            transactionRepository.save(foundTransaction);
            return new TransactionStatusData();
        } catch (NullUserException e) {
            logger.warn("Payment: User Not Found!");
            throw e;
        } catch (TransactionNotFoundException e) {
            logger.warn("Payment: Transaction Not Found!");
            throw e;
        } catch (WrongUserException e) {
            logger.warn(("Payment: Wrong User!"));
            throw e;
        } catch (WrongStatusException e) {
            logger.warn("Payment: Wrong Status!");
            throw e;
        } catch (OutOfStockException e) {
            logger.warn("Payment: Out Of Stock!");
            throw e;
        }
    }

    @Override
    public TransactionDetailData finish(FirebaseUserData userData, Integer tid) {
        try {
            UserEntity loginUser = userService.getEntityByFirebaseUser(userData);
            if (loginUser == null) { // for 100% safe
                throw new NullUserException();
            }
            TransactionEntity foundTransaction = getTransactionEntity(tid);
            if (!loginUser.getUid().equals(foundTransaction.getUser().getUid())) {
                throw new WrongUserException();
            }
            if (foundTransaction.getStatus() != Status.PROCESSING) {
                throw new WrongStatusException();
            }
            cartItemService.deleteAllCartItem(loginUser);
            foundTransaction.setStatus(Status.SUCCESS);
            return new TransactionDetailData(transactionRepository.save(foundTransaction));
        } catch (NullUserException e) {
            logger.warn("Finish: User Not Found!");
            throw e;
        } catch (TransactionNotFoundException e) {
            logger.warn("Finish: Transaction Not Found!");
            throw e;
        } catch (WrongUserException e) {
            logger.warn(("Finish: Wrong User!"));
            throw e;
        } catch (WrongStatusException e) {
            logger.warn("Finish: Wrong Status!");
            throw e;
        }
    }

    @Override
    public List<TransactionDetailData> getAllTransaction(FirebaseUserData userData) {
        UserEntity loginUser = userService.getEntityByFirebaseUser(userData);
        removeExpiredTransaction(loginUser);
        removeExpiredProccessingTransaction(loginUser);
        List<TransactionDetailData> list = new ArrayList<>();
        for (TransactionEntity entity : transactionRepository.findAllByUserAndStatusIsNot(loginUser, Status.FAIL)) {
            list.add(new TransactionDetailData(entity));
        }
        return list;
    }

    public TransactionEntity getTransactionEntity(Integer tid) {
        Optional<TransactionEntity> entity = transactionRepository.findById(tid);
        if (entity.isEmpty()) {
            throw new TransactionNotFoundException();
        }
        return entity.get();
    }

    public void removeExpiredTransaction(UserEntity loginUser) {
        List<TransactionEntity> list = transactionRepository.findAllByUserAndStatus(loginUser, Status.PREPARE);
        for (TransactionEntity entity : list) {
            if (LocalDateTime.now().isAfter(entity.getDateTime().plusSeconds(1800))) {
                entity.setStatus(Status.FAIL);
            }
        }
        transactionRepository.saveAll(list);
    }
    public void removeExpiredProccessingTransaction(UserEntity loginUser) {
        List<TransactionEntity> list = transactionRepository.findAllByUserAndStatus(loginUser, Status.PROCESSING);
        for (TransactionEntity entity : list) {
            if (LocalDateTime.now().isAfter(entity.getDateTime().plusSeconds(900))) {
                entity.setStatus(Status.FAIL);
            }
        }
        transactionRepository.saveAll(list);
    }
}
