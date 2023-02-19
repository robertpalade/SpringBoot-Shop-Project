package com.shop.project.controllers;

import com.shop.project.models.Product;
import com.shop.project.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}
