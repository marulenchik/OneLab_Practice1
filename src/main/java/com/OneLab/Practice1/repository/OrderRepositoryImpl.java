package com.OneLab.Practice1.repository;

import com.OneLab.Practice1.model.Order;
import com.OneLab.Practice1.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepositoryImpl implements OrderRepository {


    private JdbcTemplate jdbcTemplate;

    public OrderRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Order order) {
        String sql = "INSERT INTO customer_order (id, order_number, total_price) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, order.getId(), order.getOrderNumber(), order.getTotalPrice());

        // Insert into order_products join table
        for (Product product : order.getProducts()) {
            String joinSql = "INSERT INTO order_products (order_id, product_id) VALUES (?, ?)";
            jdbcTemplate.update(joinSql, order.getId(), product.getId());
        }
    }

    @Override
    public Order findById(Long id) {
        String sql = "SELECT * FROM customer_order WHERE id = ?";
        Order order = jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                new BeanPropertyRowMapper<>(Order.class)
        );

        // Retrieve products associated with the order
        String productSql = "SELECT p.* FROM product p " +
                "JOIN order_products op ON p.id = op.product_id " +
                "WHERE op.order_id = ?";
        List<Product> products = jdbcTemplate.query(
                productSql,
                new Object[]{id},
                new BeanPropertyRowMapper<>(Product.class)
        );
        order.setProducts(products);

        return order;
    }

    @Override
    public List<Order> findAll() {
        String sql = "SELECT * FROM customer_order";
        List<Order> orders = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Order.class));

        // Retrieve products for each order
        for (Order order : orders) {
            String productSql = "SELECT p.* FROM product p " +
                    "JOIN order_products op ON p.id = op.product_id " +
                    "WHERE op.order_id = ?";
            List<Product> products = jdbcTemplate.query(
                    productSql,
                    new Object[]{order.getId()},
                    new BeanPropertyRowMapper<>(Product.class)
            );
            order.setProducts(products);
        }

        return orders;
    }
}

