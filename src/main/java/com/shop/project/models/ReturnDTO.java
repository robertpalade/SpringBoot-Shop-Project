package com.shop.project.models;

import lombok.Data;

import java.util.List;

@Data
public class ReturnDTO {
    private Long customerId;
    private Long orderId;
    private List<Long> productId;
}
