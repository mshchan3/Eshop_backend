package com.fsse2212.eshop.data.cartitem;

import java.math.BigDecimal;

public class CartItemData {
    private Integer pid;
    private String name;
    private String imageUrl;
    private BigDecimal price;
    private Integer stock;
    private Integer cartQuantity;

    public CartItemData(CartItemEntity entity) {
        this.pid = entity.getProduct().getPid();
        this.name = entity.getProduct().getName();
        this.imageUrl = entity.getProduct().getImageUrl();
        this.price = entity.getProduct().getPrice();
        this.stock = entity.getProduct().getStockQuantity();
        this.cartQuantity = entity.getQuantity();
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getCartQuantity() {
        return cartQuantity;
    }

    public void setCartQuantity(Integer cartQuantity) {
        this.cartQuantity = cartQuantity;
    }
}
