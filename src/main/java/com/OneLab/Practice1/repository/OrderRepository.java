package com.OneLab.Practice1.repository;

import com.OneLab.Practice1.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findOrdersByCustomerEmail(String email);
}
