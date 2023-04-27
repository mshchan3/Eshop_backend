package com.fsse2212.eshop.service.impl;

import com.fsse2212.eshop.data.cartitem.CartItemStatusData;
import com.fsse2212.eshop.data.cartitem.CartItemData;
import com.fsse2212.eshop.data.cartitem.CartItemEntity;
import com.fsse2212.eshop.data.product.ProductEntity;
import com.fsse2212.eshop.data.user.FirebaseUserData;
import com.fsse2212.eshop.data.user.UserEntity;
import com.fsse2212.eshop.exception.*;
import com.fsse2212.eshop.repository.CartItemRepository;
import com.fsse2212.eshop.service.CartItemService;
import com.fsse2212.eshop.service.ProductService;
import com.fsse2212.eshop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {
    private final UserService userService;
    private final ProductService productService;
    private final CartItemRepository cartItemRepository;
    Logger logger = LoggerFactory.getLogger(CartItemServiceImpl.class);

    public CartItemServiceImpl(UserService userService, ProductService productService, CartItemRepository cartItemRepository) {
        this.userService = userService;
        this.productService = productService;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public CartItemStatusData addCartItem(FirebaseUserData userData, Integer pid, Integer quantity) {
        try {
            UserEntity loginUserEntity = userService.getEntityByFirebaseUser(userData);
            if (loginUserEntity == null) { // for 100% safe
                throw new NullUserException();
            }
            ProductEntity product = productService.getProductEntity(pid);
            if (quantity <= 0 || quantity > product.getStockQuantity()) {
                throw new WrongQuantityException();
            }
            Optional<CartItemEntity> cartItemEntity = cartItemRepository.findByUserAndProduct(
                    loginUserEntity, product);
            if (cartItemEntity.isEmpty()) {
                cartItemRepository.save(
                        new CartItemEntity(productService.getProductEntity(pid), loginUserEntity, quantity));
                return new CartItemStatusData();

            }
            if (product.getStockQuantity() >= cartItemEntity.get().getQuantity() + quantity) {
                cartItemEntity.get().setQuantity(cartItemEntity.get().getQuantity() + quantity);
                cartItemRepository.save(cartItemEntity.get());
                return new CartItemStatusData();
            }
            throw new OutOfStockException();
        } catch (NullUserException e){
            logger.warn("Add CartIem: User Not Found!");
            throw e;
        } catch (WrongQuantityException e){
            logger.warn("Add CartItem: Wrong Quantity");
            throw e;
        } catch (OutOfStockException e){
            logger.warn("Add CartItem: Out of Stock!");
            throw e;
        }
    }

    @Override
    public List<CartItemData> getUserCart(FirebaseUserData userData) {
        List<CartItemData> cartItemDataList = new ArrayList<>();
        for (CartItemEntity entity : cartItemRepository.findAllByUser(userService.getEntityByFirebaseUser(userData))) {
            cartItemDataList.add(new CartItemData(entity));
        }
        return cartItemDataList;
    }

    @Override
    public CartItemData updateCartQuantity(FirebaseUserData userData, Integer pid, Integer quantity) {
        try {
            UserEntity loginUserEntity = userService.getEntityByFirebaseUser(userData);
            if (loginUserEntity == null) { // for 100% safe
                throw new NullUserException();
            }
            ProductEntity product = productService.getProductEntity(pid);
            Optional<CartItemEntity> cartItemEntity = cartItemRepository.findByUserAndProduct(
                    loginUserEntity, product);
            if (cartItemEntity.isEmpty()) {
                throw new CartItemNotFoundException();
            }
            if (quantity == 0) {
                cartItemEntity.get().setQuantity(quantity);
                cartItemRepository.delete(cartItemEntity.get());
                return new CartItemData(cartItemEntity.get());
            }
            if (product.getStockQuantity() < quantity) {
                throw new OutOfStockException();
            } else {
                cartItemEntity.get().setQuantity(quantity);
                return new CartItemData(cartItemRepository.save(cartItemEntity.get()));
            }
        }catch (NullUserException e){
            logger.warn("Update CartIem: User Not Found!");
            throw e;
        } catch (CartItemNotFoundException e){
            logger.warn("Update CartItem: Cart Item Not Found");
            throw e;
        } catch (OutOfStockException e){
            logger.warn("Update CartItem: Out of Stock!");
            throw e;
        }
    }

    @Override
    public CartItemStatusData deleteCartItem(FirebaseUserData userData, Integer pid) {
        try {
            UserEntity loginUserEntity = userService.getEntityByFirebaseUser(userData);
            if (loginUserEntity == null) { // for 100% safe
                throw new NullUserException();
            }
            ProductEntity product = productService.getProductEntity(pid);
            Optional<CartItemEntity> cartItemEntity = cartItemRepository.findByUserAndProduct(
                    loginUserEntity, product);
            if (cartItemEntity.isEmpty()) {
                throw new CartItemNotFoundException();
            }
            cartItemRepository.delete(cartItemEntity.get());
            return new CartItemStatusData();
        } catch (NullUserException e){
            logger.warn("Delete CartItem: User Not Found!");
            throw e;
        } catch (CartItemNotFoundException e){
            logger.warn("Delete CartItem: Cart Item Not Found");
            throw e;
        }
    }

    @Override
    public void deleteAllCartItem(UserEntity user) {
        Iterable<CartItemEntity> list = cartItemRepository.findAllByUser(user);
        cartItemRepository.deleteAll(list);
    }

    @Override
    public List<CartItemEntity> getAllCartItem(UserEntity user) {
        return cartItemRepository.findAllByUser(user);
    }
}
