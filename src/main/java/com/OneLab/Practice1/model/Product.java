package com.OneLab.Practice1.model;

import lombok.Data;

@Data
public class Product {
    private Long id;
    private String productName;
    private String productDescription;
    private int articul;
    private int quantity;
    private double price;
}

