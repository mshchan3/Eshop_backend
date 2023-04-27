package com.fsse2212.eshop.service.impl;

import com.fsse2212.eshop.data.product.CreateProductData;
import com.fsse2212.eshop.data.product.ProductData;
import com.fsse2212.eshop.data.product.ProductDataHasStock;
import com.fsse2212.eshop.data.product.ProductEntity;
import com.fsse2212.eshop.exception.ProductNotFoundException;
import com.fsse2212.eshop.repository.ProductRepository;
import com.fsse2212.eshop.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDataHasStock addProduct(CreateProductData data){
        return new ProductDataHasStock(productRepository.save(new ProductEntity(data)));
    }

    @Override
    public List<ProductDataHasStock> getAllProduct(){
        List<ProductDataHasStock> productDataHasStockList = new ArrayList<>();
        for (ProductEntity entity: productRepository.findAll()) {
            productDataHasStockList.add(new ProductDataHasStock(entity));
        }
        return productDataHasStockList;
    }

    @Override
    public ProductData getProductById(Integer pid){
        return new ProductData(getProductEntity(pid));
    }

    @Override
    public ProductEntity getProductEntity(Integer pid){
        Optional<ProductEntity> entity = productRepository.findById(pid);
        if (entity.isPresent()){
            return entity.get();
        }
        logger.warn("Product Not Found!");
        throw new ProductNotFoundException();
    }

    @Override
    public void setProductQuantity(Integer quantity, Integer pid){
        ProductEntity product = getProductEntity(pid);
        product.setStockQuantity(product.getStockQuantity() - quantity);
        productRepository.save(product);
    }
}
