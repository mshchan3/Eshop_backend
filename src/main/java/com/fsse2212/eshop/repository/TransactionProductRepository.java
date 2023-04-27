package com.fsse2212.eshop.repository;

import com.fsse2212.eshop.data.transactionproduct.TransactionProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface TransactionProductRepository extends CrudRepository<TransactionProductEntity, Integer> {
}
