package com.fsse2212.eshop.data.transaction;

import com.fsse2212.eshop.data.transactionproduct.TransactionProductEntity;
import com.fsse2212.eshop.data.user.UserEntity;
import com.fsse2212.eshop.service.impl.Status;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tid;
    @ManyToOne
    private UserEntity user;
    @Column(nullable = false)
    private LocalDateTime dateTime;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(nullable = false)
    private BigDecimal total;
    @OneToMany(mappedBy = "transaction")
    private List<TransactionProductEntity> transactionProduct;


    public TransactionEntity(UserEntity user) {
        this.user = user;
        this.dateTime = LocalDateTime.now();
        this.status = Status.PREPARE;
        this.total = BigDecimal.valueOf(0);
        this.transactionProduct = new ArrayList<>();
    }

    public TransactionEntity() {

    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
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

    public List<TransactionProductEntity> getTransactionProduct() {
        return transactionProduct;
    }

    public void setTransactionProduct(List<TransactionProductEntity> transactionProduct) {
        this.transactionProduct = transactionProduct;
    }
}
