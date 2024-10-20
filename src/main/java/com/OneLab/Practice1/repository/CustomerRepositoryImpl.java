package com.OneLab.Practice1.repository;

import com.OneLab.Practice1.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    private JdbcTemplate jdbcTemplate;

    public CustomerRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Customer customer) {
        String sql = "INSERT INTO customer (id, name, email, password) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, customer.getId(), customer.getName(), customer.getEmail(), customer.getPassword());
    }

    @Override
    public Customer findById(Long id) {
        String sql = "SELECT * FROM customer WHERE id = ?";
        return jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                new BeanPropertyRowMapper<>(Customer.class)
        );
    }

    @Override
    public List<Customer> findAll() {
        String sql = "SELECT * FROM customer";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Customer.class));
    }
}



