package com.OneLab.Practice1.repository;

import com.OneLab.Practice1.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
