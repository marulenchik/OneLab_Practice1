package com.OneLab.Practice1.model;

import lombok.Builder;
import lombok.Data;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long id;
    private String name;
    private Double price;
}


