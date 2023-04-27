package com.fsse2212.eshop.data.transactionproduct;

import com.fsse2212.eshop.data.product.ProductData;

import java.math.BigDecimal;

public class TransactionProductDetailData {
    private Integer tpid;
    private ProductData product;
    private Integer quantity;
    private BigDecimal subTotal;

    public TransactionProductDetailData(TransactionProductEntity entity) {
        this.tpid = entity.getTpid();
        this.quantity = entity.getQuantity();
        this.subTotal = entity.getSubTotal();
        setProduct(entity);
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

    public void setProduct(TransactionProductEntity entity){
        this.product = new ProductData(
                entity.getPid(), entity.getName(), entity.getImageUrl(), entity.getPrice(), entity.getStockQuantity(), entity.getCategory(), entity.getBrand(), entity.getPriceId()
        );
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
