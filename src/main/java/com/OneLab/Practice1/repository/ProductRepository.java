package com.OneLab.Practice1.repository;

import com.OneLab.Practice1.model.Product;
import java.util.List;

public interface ProductRepository {
    Product findById(Long id);
    List<Product> findAll();
    void save(Product item);
}
