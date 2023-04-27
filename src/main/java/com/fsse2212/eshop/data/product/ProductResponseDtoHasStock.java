package com.fsse2212.eshop.data.product;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class ProductResponseDtoHasStock {
    private Integer pid;
    private String name;
    private BigDecimal price;
    @JsonProperty("image_url")
    private String imageUrl;
    @JsonProperty("has_stock")
    private Boolean hasStock;
    private String category;
    private String brand;

    public ProductResponseDtoHasStock(ProductDataHasStock data) {
        this.pid = data.getPid();
        this.name = data.getName();
        this.price = data.getPrice();
        this.imageUrl = data.getImageUrl();
        this.hasStock = data.getHasStock();
        this.category = data.getCategory();
        this.brand = data.getBrand();
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

    public Boolean getHasStock() {
        return hasStock;
    }

    public void setHasStock(Boolean hasStock) {
        this.hasStock = hasStock;
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
}
