package com.OneLab.Practice1.repository;

import com.OneLab.Practice1.model.Product;

import java.util.List;

public interface ProductRepository {
    void save(Product product);
    Product findById(Long id);
    List<Product> findAll();
    void deleteById(Long id);
}

