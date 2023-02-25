package com.shop.project.controllers;

import com.shop.project.models.*;
import com.shop.project.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/getOrders")
    public List<Order> getOrders(){
        return orderService.getOrders();
    }

    @GetMapping("/getOrderById/{id}")
    public Order getOrderById(@PathVariable Long id){
        return orderService.getOrderById(id);
    }

    @GetMapping("/getOrdersByProductCategory")
    public List<Order> getOrdersByProductCategory(){
        return orderService.getOrdersByProductCategory();
    }

    @GetMapping("/getProductsByOrderId")
    public Set<Product> getProductsByOrderId(@PathVariable Long id) {
        return orderService.getProductsByOrderId(id);
    }

    @GetMapping("/getProductsByDate")
    public List<Product> getProductsByDate(){
        return orderService.getProductsByDate();
    }

    @GetMapping("/getRecentOrders")
    public List<Order> getRecentOrders(){
        return orderService.getRecentOrders();
    }

    @GetMapping("/getAverageByDate")
    public Double getAverageByDate(){
        return orderService.getAverageByDate();
    }

    @GetMapping("/getOrderIdAndCount")
    public Map<Long, Integer> getOrderIdAndCount(){
        return orderService.getOrderIdAndCount();
    }

    @GetMapping("/getOrderByCustomer")
    public Map<Customer, List<Order>> getOrderByCustomer(){
        return orderService.getOrderByCustomer();
    }

    @PostMapping("/makeOrder")
    public Order makeOrder(@RequestBody OrderDTO orderDTO){
        return orderService.makeOrder(orderDTO);
    }

    @PostMapping("/makeReturn")
    public Set<Product> makeReturn(@RequestBody ReturnDTO returnDTO){
        return orderService.makeReturn(returnDTO);
    }


}
