package com.fsse2212.eshop.service;

import com.fsse2212.eshop.data.transactionproduct.TransactionProductEntity;

import java.util.List;

public interface TransactionProductService {
    List<TransactionProductEntity> saveList(List<TransactionProductEntity> list);
}
