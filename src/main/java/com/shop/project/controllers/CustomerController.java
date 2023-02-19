package com.shop.project.controllers;

import com.shop.project.models.Customer;
import com.shop.project.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/getCustomers")
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }

    @GetMapping("/getCustomerById/{id}")
    public Customer getCustomerById(@PathVariable Long id){
        return customerService.getCustomerById(id);
    }
}
