package com.fsse2212.eshop.data.transactionproduct;

import com.fsse2212.eshop.data.product.ProductData;

import java.math.BigDecimal;

public class TransactionProductResponseDto {
    private Integer tpid;
    private ProductData product;
    private Integer quantity;
    private BigDecimal subTotal;

    public TransactionProductResponseDto(TransactionProductDetailData data) {
        this.tpid = data.getTpid();
        this.product = data.getProduct();
        this.quantity = data.getQuantity();
        this.subTotal = data.getSubTotal();
    }

    public Integer getTpid() {
        return tpid;
    }

    public void setTpid(Integer tpid) {
        this.tpid = tpid;
    }

    public ProductData getProduct() {
        return product;
    }

    public void setProduct(ProductData product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }
}
