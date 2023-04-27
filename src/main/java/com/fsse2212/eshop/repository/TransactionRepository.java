package com.fsse2212.eshop.repository;

import com.fsse2212.eshop.data.transaction.TransactionEntity;
import com.fsse2212.eshop.data.user.UserEntity;
import com.fsse2212.eshop.service.impl.Status;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<TransactionEntity, Integer> {
    List<TransactionEntity> findAllByUserAndStatus(UserEntity user, Status status);
    List<TransactionEntity> findAllByUserAndStatusIsNot(UserEntity user, Status status);
}
