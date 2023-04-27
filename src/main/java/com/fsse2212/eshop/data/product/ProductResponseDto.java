package com.fsse2212.eshop.data.product;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class ProductResponseDto {
    private Integer pid;
    private String name;
    private BigDecimal price;
    @JsonProperty("image_url")
    private String imageUrl;
    private Integer stock;
    private String category;
    private String brand;
    private String priceId;

    public ProductResponseDto(ProductData data) {
        this.pid = data.getPid();
        this.name = data.getName();
        this.price = data.getPrice();
        this.imageUrl = data.getImageUrl();
        this.stock = data.getStock();
        this.category = data.getCategory();
        this.brand = data.getBrand();
        this.priceId = data.getPriceId();
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
