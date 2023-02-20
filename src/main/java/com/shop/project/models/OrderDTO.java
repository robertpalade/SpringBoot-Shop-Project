package com.shop.project.models;

import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {
    private Long customerId;
    private List<Long> productId;
}
