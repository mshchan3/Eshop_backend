package com.fsse2212.eshop.data.product;

import java.math.BigDecimal;

public class ProductDataHasStock {
    private Integer pid;
    private String name;
    private String imageUrl;
    private BigDecimal price;
    private Boolean hasStock;
    private String category;
    private String brand;

    public ProductDataHasStock(ProductEntity entity) {
        this.pid = entity.getPid();
        this.name = entity.getName();
        this.imageUrl = entity.getImageUrl();
        this.price = entity.getPrice();
        this.hasStock = checkHasStock(entity);
        this.category = entity.getCategory();
        this.brand = entity.getBrand();
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

    public Boolean checkHasStock(ProductEntity entity){
        return entity.getStockQuantity() > 0;
    }
}
