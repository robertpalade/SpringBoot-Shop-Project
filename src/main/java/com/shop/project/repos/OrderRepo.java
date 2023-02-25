package com.shop.project.repos;

import java.util.List;

import com.shop.project.models.Customer;
import com.shop.project.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {

    List<Order> findAll();
    List<Order> findByCustomer(Customer customer);

}
