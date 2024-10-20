package com.OneLab.Practice1.model;

import lombok.Builder;
import lombok.Data;
import java.util.List;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private Long id;
    private String name;
    private String email;
    private String password;
    private List<Order> orders;
}

