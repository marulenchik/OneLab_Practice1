package com.OneLab.Practice1.model;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class Order {
    private Long id;
    private List<Product> products;
    private String orderNumber;
    private Customer customer;
    private int totalPrice;
}

