package com.OneLab.Practice1.model;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class Customer {
    private Long id;
    private String name;
    private String email;
    private String password;
    private List<Order> orders;

}

