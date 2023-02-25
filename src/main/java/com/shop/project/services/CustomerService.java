package com.shop.project.services;

import com.shop.project.models.Customer;
import com.shop.project.models.Product;
import com.shop.project.models.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shop.project.repos.CustomerRepo;
import com.shop.project.repos.OrderRepo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CustomerService {
    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    OrderRepo orderRepo;

    public List<Customer> getCustomers() {
        return customerRepo.findAll()
                .stream()
                .collect(Collectors.toList());
    }

    public Customer getCustomerById(Long id) {
        Optional<Customer> customer = customerRepo.findById(id);

        if(customer.isEmpty()) throw new NullPointerException();

        return  customer.get();
    }

    public void changeStatus(Customer customer) {

        double totalOrderAmount = getTotalOrderAmount(customer);
//        log.info(String.valueOf(totalOrderAmount));

        customer.setStatus(Status.setPlafon(totalOrderAmount));
//                    customer.setTier(Status.setPlafon(totalOrderAmount));
        customerRepo.save(customer);
//                    double totalSpentAmount = 0;
//                    List<Order> orderList = orderRepos.findByCustomer(customer);
//                    for(Order order:orderList){
//                        Set<Product> productList = order.getProducts();
//                        double orderTotal = 0;
//                        for(Product product:productList){
//                            orderTotal += product.getPrice();
//                        }
//                        totalSpentAmount += orderTotal;
//                    }
    }

    private double getTotalOrderAmount(Customer customer) {
        return orderRepo.findByCustomer(customer).stream()
                .flatMapToDouble(order ->
                        order.getProducts().stream()
                                .mapToDouble(Product::getPrice))
                .sum();
    }


}
