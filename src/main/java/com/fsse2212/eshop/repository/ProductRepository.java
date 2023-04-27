package com.fsse2212.eshop.repository;

import com.fsse2212.eshop.data.product.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {
}
