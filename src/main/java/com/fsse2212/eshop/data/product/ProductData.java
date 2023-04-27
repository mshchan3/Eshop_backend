package com.fsse2212.eshop.data.product;

import java.math.BigDecimal;

public class ProductData {
    private Integer pid;
    private String name;
    private String imageUrl;
    private BigDecimal price;
    private Integer stock;
    private String category;
    private String brand;
    private String priceId;

    public ProductData(ProductEntity entity) {
        this.pid = entity.getPid();
        this.name = entity.getName();
        this.imageUrl = entity.getImageUrl();
        this.price = entity.getPrice();
        this.stock = entity.getStockQuantity();
        this.category = entity.getCategory();
        this.brand = entity.getBrand();
        this.priceId = entity.getPriceId();
    }

    public ProductData(Integer pid, String name, String imageUrl, BigDecimal price, Integer stock, String category, String brand, String priceId) {
        this.pid = pid;
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.brand = brand;
        this.priceId = priceId;
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
