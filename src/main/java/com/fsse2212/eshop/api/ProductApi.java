package com.fsse2212.eshop.api;

import com.fsse2212.eshop.config.EnvConfig;
import com.fsse2212.eshop.data.product.*;
import com.fsse2212.eshop.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/public/product")
@CrossOrigin({EnvConfig.devBaseUrl, EnvConfig.prodBaseUrl})
public class ProductApi {
    private final ProductService productService;

    public ProductApi(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add-product")
    public ProductResponseDtoHasStock addProduct(@RequestBody ProductRequestDto dto){
        return new ProductResponseDtoHasStock(productService.addProduct(new CreateProductData(dto)));
    }

//    @GetMapping
//    public List<ProductResponseDtoHasStock> getAllProduct(){
//        List<ProductResponseDtoHasStock> dtoList = new ArrayList<>();
//        for (ProductDataHasStock data : productService.getAllProduct()) {
//            dtoList.add(new ProductResponseDtoHasStock(data));
//        }
//        return dtoList;
//    }

    public String getAllProduct(){
        return "Fuck!!!!!!";
    }

    @GetMapping("/{pid}")
    public ProductResponseDto getProductById(@PathVariable Integer pid){
        return new ProductResponseDto(productService.getProductById(pid));
    }
}


