package com.shop.project.controllers;

import com.shop.project.models.Order;
import com.shop.project.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}
