package com.fsse2212.eshop.data.cartitem;

import com.fsse2212.eshop.data.product.ProductEntity;
import com.fsse2212.eshop.data.user.UserEntity;
import jakarta.persistence.*;

@Entity
public class CartItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cid;
    @ManyToOne
    @JoinColumn(name = "pid", nullable = false)
    private ProductEntity product;
    @ManyToOne
    @JoinColumn(name = "uid", nullable = false)
    private UserEntity user;
    @Column(nullable = false)
    private Integer quantity;

    public CartItemEntity(ProductEntity product, UserEntity user, Integer quantity) {
        this.product = product;
        this.user = user;
        this.quantity = quantity;
    }

    public CartItemEntity() {

    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
