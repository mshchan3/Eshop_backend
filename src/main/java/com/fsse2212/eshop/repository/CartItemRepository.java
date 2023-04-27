package com.fsse2212.eshop.repository;

import com.fsse2212.eshop.data.cartitem.CartItemEntity;
import com.fsse2212.eshop.data.product.ProductEntity;
import com.fsse2212.eshop.data.user.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends CrudRepository<CartItemEntity, Integer> {
    Optional<CartItemEntity> findByUserAndProduct(UserEntity user, ProductEntity product);
    List<CartItemEntity> findAllByUser(UserEntity user);
}
