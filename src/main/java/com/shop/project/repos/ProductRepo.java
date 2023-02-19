package com.shop.project.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shop.project.models.Product;

@Repository
public interface ProductRepo extends CrudRepository<Product, Long> {

    List<Product> findAll();
}
