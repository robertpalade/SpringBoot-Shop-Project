package com.shop.project.services;

import com.shop.project.models.Product;
import com.shop.project.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
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

    public List<Product> getProductsByCategory(String category){
        return productRepo.findAll()
                .stream()
                .filter(p -> p.getCategory().equalsIgnoreCase("category"))
                .collect(Collectors.toList());
    }

    public List<Product> getToys() {
        return productRepo.findAll()
                .stream()
                .filter(p -> p.getCategory().equalsIgnoreCase("Toys"))
                .map(p -> p.withPrice(p.getPrice() - p.getPrice() * 0.1))
                .collect(Collectors.toList());
    }

    public Optional<Product> getCheapestBook(){
        return productRepo.findAll()
                .stream()
                .filter(p -> p.getCategory().equalsIgnoreCase("Books"))
                .sorted(Comparator.comparing(Product::getPrice))
                .findFirst();
    }

    public Map<String, List<String>> getProductByCategory(){
        return productRepo.findAll()
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Product::getCategory,
                                Collectors.mapping(product -> product.getName(), Collectors.toList()))
                );
    }

    public Map<String, Optional<Product>> getMostExpensiveProductByCategory(){
        return productRepo.findAll()
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Product::getCategory,
                                Collectors.maxBy(Comparator.comparing(Product::getPrice))
                        )
                );
    }
}
