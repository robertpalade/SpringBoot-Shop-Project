package com.shop.project.repos;

import java.util.List;

import com.shop.project.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {

    List<Customer> findAll();
}
