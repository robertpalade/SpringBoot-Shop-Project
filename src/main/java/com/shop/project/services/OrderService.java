package com.shop.project.services;

import com.shop.project.models.*;
import com.shop.project.repos.OrderRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {
    final OrderRepo orderRepo;

    final CustomerService customerService;

    final ProductService productService;


    public OrderService(OrderRepo orderRepo, CustomerService customerService, ProductService productService) {
        this.orderRepo = orderRepo;
        this.customerService = customerService;
        this.productService = productService;
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

    public Order makeOrder(OrderDTO orderDTO) {
        // orderDTO.getCustomerId->customer

        Customer customer = customerService.getCustomerById(orderDTO.getCustomerId());
        // similar Set<Product>
        Set<Product> productList = orderDTO.getProductId().stream()
                .map(productService::getProductById)
                .collect(Collectors.toSet());


        Order order = new Order();
        order.setCustomer(customer);
        order.setProducts(productList);

        LocalDate now = LocalDate.now();
        order.setOrderDate(now);

        order.setDeliveryDate(now.plusDays(2));
        order.setStatus("NEW");

        orderRepo.save(order);
        return order;
    }

    public Set<Product> makeReturn(ReturnDTO returnDTO) {
        Customer customer = customerService.getCustomerById(returnDTO.getCustomerId());

        Order order = this.getOrderById(returnDTO.getOrderId());

        Set<Product> productListToBeReturned = returnDTO.getProductId().stream()
                .map(productService::getProductById)
                .collect(Collectors.toSet());
        Set<Product> productListFromOrder = this.getProductsByOrderId(returnDTO.getOrderId());
        productListFromOrder.removeAll(productListToBeReturned);

        order.setProducts(productListFromOrder);

        orderRepo.save(order);
        return order.getProducts();

    }







}
