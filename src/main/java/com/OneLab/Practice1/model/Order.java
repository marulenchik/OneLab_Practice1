package com.OneLab.Practice1.model;

import lombok.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Long id;
    private String orderNumber;
    private int totalPrice;
    private List<Product> products;
}

