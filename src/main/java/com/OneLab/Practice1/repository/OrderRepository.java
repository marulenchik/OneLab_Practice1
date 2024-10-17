package com.OneLab.Practice1.repository;

import com.OneLab.Practice1.model.Order;
import java.util.List;

public interface OrderRepository {
    Order findById(Long id);
    List<Order> findAll();
    void save(Order order);
}
