package com.OneLab.Practice1.repository;

import com.OneLab.Practice1.model.Customer;
import java.util.List;

public interface CustomerRepository {
    Customer findById(Long id);
    List<Customer> findAll();
    void save(Customer customer);
}
