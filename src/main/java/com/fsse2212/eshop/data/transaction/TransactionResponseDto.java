package com.fsse2212.eshop.data.transaction;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsse2212.eshop.data.transactionproduct.TransactionProductDetailData;
import com.fsse2212.eshop.data.transactionproduct.TransactionProductResponseDto;
import com.fsse2212.eshop.service.impl.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionResponseDto {
    private Integer tid;
    @JsonProperty("buyer_uid")
    private Integer uid;
    @JsonFormat(pattern = "yyyyMMdd'T'HH:MM:SS", timezone = "GMT")
    private LocalDateTime datetime;
    private Status status;
    private BigDecimal total;
    @JsonProperty("items")
    private List<TransactionProductResponseDto> product;

    public TransactionResponseDto(TransactionDetailData data) {
        this.tid = data.getTid();
        this.uid = data.getUid();
        this.datetime = data.getDatetime();
        this.status = data.getStatus();
        this.total = data.getTotal();
        setProduct(data);
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

    public List<TransactionProductResponseDto> getProduct() {
        return product;
    }

    public void setProduct(List<TransactionProductResponseDto> product) {
        this.product = product;
    }

    public void setProduct(TransactionDetailData data){
        List<TransactionProductResponseDto> list = new ArrayList<>();
        for (TransactionProductDetailData detailData: data.getProduct()) {
            list.add(new TransactionProductResponseDto(detailData));
        }
        this.product = list;
    }
}
