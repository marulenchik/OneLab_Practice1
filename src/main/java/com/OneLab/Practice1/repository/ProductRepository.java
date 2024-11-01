package com.OneLab.Practice1.repository;

import com.OneLab.Practice1.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

