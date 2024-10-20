package com.OneLab.Practice1.service;

import com.OneLab.Practice1.model.Customer;
import com.OneLab.Practice1.model.Order;
import com.OneLab.Practice1.model.Product;
import com.OneLab.Practice1.repository.CustomerRepository;
import com.OneLab.Practice1.repository.OrderRepository;
import com.OneLab.Practice1.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository,
                           OrderRepository orderRepository,
                           ProductRepository productRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    // Method for customer to place an order
    public void placeOrder(Long customerId, List<Long> productIds) {
        Customer customer = customerRepository.findById(customerId);
        if (customer == null) {
            System.out.println("Customer not found with ID: " + customerId);
            return;
        }

        List<Product> products = productIds.stream()
                .map(productRepository::findById)
                .filter(p -> p != null)
                .toList();

        if (products.isEmpty()) {
            System.out.println("No valid products found for the order.");
            return;
        }

        Order order = Order.builder()
                .id(generateOrderId())
                .orderNumber("ORD" + System.currentTimeMillis())
                .products(products)
                .build();

        orderRepository.save(order);

        // Add order to customer's list of orders
        if (customer.getOrders() == null) {
            customer.setOrders(new ArrayList<>());
        }
        customer.getOrders().add(order);

        System.out.println("Order placed successfully for customer ID: " + customerId);
    }

    private Long generateOrderId() {
        return System.currentTimeMillis();
    }
}


