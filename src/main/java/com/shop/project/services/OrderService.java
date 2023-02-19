package com.shop.project.services;

import com.shop.project.models.Customer;
import com.shop.project.models.Order;
import com.shop.project.models.Product;
import com.shop.project.repos.OrderRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
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

    public List<Order> getOrdersByProductCategory() {
        return orderRepo.findAll()
                .stream()
                .filter(o ->
                        o.getProducts()
                                .stream()
                                .anyMatch(p -> p.getCategory().equalsIgnoreCase("Baby"))
                )
                .collect(Collectors.toList());
    }

    public Set<Product> getProductsByOrderId(Long id) {
        return getOrderById(id).getProducts();
    }

    public List<Product> getProductsByDate() {
        return orderRepo.findAll()
                .stream()
                .filter(o -> o.getOrderDate().compareTo(LocalDate.of(2021, 2, 1)) >= 0)
                .filter(o -> o.getOrderDate().compareTo(LocalDate.of(2021, 4, 1)) <= 0)
                .flatMap(o -> o.getProducts().stream())
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Order> getRecentOrders() {
        return orderRepo.findAll()
                .stream()
                .sorted(Comparator.comparing(Order::getOrderDate).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }

    public Double getAverageByDate() {
        return orderRepo.findAll()
                .stream()
                .filter(o -> o.getOrderDate().isEqual(LocalDate.of(2021, 3, 15)))
                .flatMap(o -> o.getProducts().stream())
                .mapToDouble(p -> p.getPrice())
                .average().getAsDouble();
    }

    public Map<Long, Integer> getOrderIdAndCount() {
        return orderRepo.findAll()
                .stream()
                .collect(
                        Collectors.toMap(
                                order -> order.getId(),
                                order -> order.getProducts().size()
                        )
                );
    }

    public Map<Customer, List<Order>> getOrderByCustomer() {
        return orderRepo.findAll()
                .stream()
                .collect(Collectors.groupingBy(Order::getCustomer));
    }







}
