package com.fsse2212.eshop.service;

import com.fsse2212.eshop.data.product.CreateProductData;
import com.fsse2212.eshop.data.product.ProductData;
import com.fsse2212.eshop.data.product.ProductDataHasStock;
import com.fsse2212.eshop.data.product.ProductEntity;

import java.util.List;

public interface ProductService {
    ProductDataHasStock addProduct(CreateProductData data);

    List<ProductDataHasStock> getAllProduct();

    ProductData getProductById(Integer pid);

    ProductEntity getProductEntity(Integer pid);

    void setProductQuantity(Integer quantity, Integer pid);
}
