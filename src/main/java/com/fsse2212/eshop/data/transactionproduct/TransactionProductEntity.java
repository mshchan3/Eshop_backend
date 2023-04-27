package com.fsse2212.eshop.data.transactionproduct;

import com.fsse2212.eshop.data.cartitem.CartItemEntity;
import com.fsse2212.eshop.data.transaction.TransactionEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class TransactionProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tpid;
    @Column(nullable = false)
    private Integer pid;
    @Column(nullable = false)
    private String name;
    private String description;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(name = "image_url", nullable = false)
    private String imageUrl;
    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity;
    @Column(nullable = false)
    private Integer quantity;
    @Column(nullable = false)
    private BigDecimal subTotal;
    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private String brand;
    @Column(nullable = false)
    private String priceId;
    @ManyToOne
    private TransactionEntity transaction;

    public TransactionProductEntity(CartItemEntity cartItem, TransactionEntity transaction) {
        this.pid = cartItem.getProduct().getPid();
        this.name = cartItem.getProduct().getName();
        this.description = cartItem.getProduct().getDescription();
        this.price = cartItem.getProduct().getPrice();
        this.imageUrl = cartItem.getProduct().getImageUrl();
        this.stockQuantity = cartItem.getProduct().getStockQuantity();
        this.quantity = cartItem.getQuantity();
        this.subTotal = cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity()));
        this.category = cartItem.getProduct().getCategory();
        this.brand = cartItem.getProduct().getBrand();
        this.priceId = cartItem.getProduct().getPriceId();
        this.transaction = transaction;
    }

    public TransactionProductEntity() {

    }

    public Integer getTpid() {
        return tpid;
    }

    public void setTpid(Integer tpid) {
        this.tpid = tpid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPriceId() {
        return priceId;
    }

    public void setPriceId(String priceId) {
        this.priceId = priceId;
    }
}
