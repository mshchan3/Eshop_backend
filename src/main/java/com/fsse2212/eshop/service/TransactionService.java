package com.fsse2212.eshop.service;

import com.fsse2212.eshop.data.transaction.TransactionDetailData;
import com.fsse2212.eshop.data.transaction.TransactionEntity;
import com.fsse2212.eshop.data.transaction.TransactionStatusData;
import com.fsse2212.eshop.data.user.FirebaseUserData;

import java.util.List;

public interface TransactionService {
    TransactionDetailData createTransaction(FirebaseUserData userData);

    TransactionDetailData getTransaction(FirebaseUserData userData, Integer tid);

    TransactionStatusData payment(FirebaseUserData userData, Integer tid);

    TransactionDetailData finish(FirebaseUserData userData, Integer tid);

    List<TransactionDetailData> getAllTransaction(FirebaseUserData userData);
}
