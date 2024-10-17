package com.OneLab.Practice1.repository;

import com.OneLab.Practice1.model.Order;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;


@Data
@Builder
@Repository
public class OrderRepositoryImpl implements OrderRepository {
    private List<Order> orderList;

    @Override
    public Order findById(Long id) {
        return orderList.stream()
                .filter(o -> o.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Order> findAll() {
        return new ArrayList<>(orderList);
    }

    @Override
    public void save(Order order) {
        orderList.add(order);
    }
}
