package com.shop.project.services;

import com.shop.project.models.Order;
import com.shop.project.repos.OrderRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    final OrderRepo orderRepo;

    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    public List<Order> getOrders() {
        return orderRepo.findAll().stream()
                .collect(Collectors.toList());
    }

    public Order getOrderById(Long id) {
        Optional<Order> order = orderRepo.findById(id);
        if(order.isEmpty()) throw new NullPointerException();
        return order.get();
    }
}
