package com.OneLab.Practice1.repository;

import com.OneLab.Practice1.model.Product;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private List<Product> productList;

    @Override
    public Product findById(Long id) {
        return productList.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(productList);
    }

    @Override
    public void save(Product product) {
        productList.add(product);
    }
}
