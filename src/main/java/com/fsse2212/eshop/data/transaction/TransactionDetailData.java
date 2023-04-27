package com.fsse2212.eshop.data.transaction;

import com.fsse2212.eshop.data.transactionproduct.TransactionProductDetailData;
import com.fsse2212.eshop.data.transactionproduct.TransactionProductEntity;
import com.fsse2212.eshop.service.impl.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionDetailData {
    private Integer tid;
    private Integer uid;
    private LocalDateTime datetime;
    private Status status;
    private BigDecimal total;
    private List<TransactionProductDetailData> product;

    public TransactionDetailData(TransactionEntity entity) {
        this.tid = entity.getTid();
        this.uid = entity.getUser().getUid();
        this.datetime = entity.getDateTime();
        this.status = entity.getStatus();
        this.total = entity.getTotal();
        setProduct(entity);
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<TransactionProductDetailData> getProduct() {
        return product;
    }

    public void setProduct(List<TransactionProductDetailData> product) {
        this.product = product;
    }

    public void setProduct(TransactionEntity entity){
        List<TransactionProductDetailData> list = new ArrayList<>();
        for (TransactionProductEntity entities: entity.getTransactionProduct()) {
            list.add(new TransactionProductDetailData(entities));
        }
        this.product = list;
    }

}
