package com.shop.project.services;

import com.shop.project.models.Product;
import com.shop.project.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> getProducts(){
        return productRepo.findAll().stream()
                .collect(Collectors.toList());
    }
    public Product getProductById(Long id){
        Optional<Product> product = productRepo.findById(id);
        if(product.isEmpty()){
            throw new NullPointerException();
        }
        return product.get();
    }
}
