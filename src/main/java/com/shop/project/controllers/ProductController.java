package com.shop.project.controllers;

import com.shop.project.models.Product;
import com.shop.project.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/getProducts")
    public List<Product> getProducts(){
        return productService.getProducts();
    }

    @GetMapping("/getProductById/{id}")
    public Product getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @GetMapping("/getProductsByCategory/{category}")
    public List<Product> getProductsByCategory(@PathVariable String category){
        return productService.getProductsByCategory(category);
    }

    @GetMapping("/getToys")
    public List<Product> getToys(){
        return productService.getToys();
    }

    @GetMapping("/getCheapestBook")
    public Optional<Product> getCheapestBook(){
        return productService.getCheapestBook();
    }

    @GetMapping("/getProductByCategory")
    public Map<String, List<String>> getProductByCategory(){
        return productService.getProductByCategory();
    }

    @GetMapping("/getMostExpensiveProductByCategory")
    public Map<String, Optional<Product>> getMostExpensiveProductByCategory(){
        return productService.getMostExpensiveProductByCategory();
    }

}
