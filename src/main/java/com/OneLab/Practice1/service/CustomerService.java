package com.OneLab.Practice1.service;

import com.OneLab.Practice1.model.Customer;
import com.OneLab.Practice1.model.Order;
import com.OneLab.Practice1.repository.CustomerRepository;
import com.OneLab.Practice1.repository.OrderRepository;
import com.OneLab.Practice1.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;
    private OrderRepository orderRepository;
    private ProductRepository productRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, OrderRepository orderRepository, ProductRepository productRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public void addCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void placeOrder() {
    }
}

