package com.fsse2212.eshop.service.impl;

import com.fsse2212.eshop.data.transactionproduct.TransactionProductEntity;
import com.fsse2212.eshop.repository.TransactionProductRepository;
import com.fsse2212.eshop.service.TransactionProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionProductServiceImpl implements TransactionProductService {
    private final TransactionProductRepository transactionProductRepository;

    public TransactionProductServiceImpl(TransactionProductRepository transactionProductRepository) {
        this.transactionProductRepository = transactionProductRepository;
    }

    @Override
    public List<TransactionProductEntity> saveList(List<TransactionProductEntity> list){
        return (List<TransactionProductEntity>) transactionProductRepository.saveAll(list);
    }
}
