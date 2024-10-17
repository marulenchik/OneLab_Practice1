package com.OneLab.Practice1.repository;

import com.OneLab.Practice1.model.Customer;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
    private List<Customer> customerList = new ArrayList<>();

    @Override
    public Customer findById(Long id) {
        return customerList.stream().filter(c -> c.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<Customer> findAll() {
        return customerList;
    }

    @Override
    public void save(Customer customer) {
        customerList.add(customer);
    }
}

