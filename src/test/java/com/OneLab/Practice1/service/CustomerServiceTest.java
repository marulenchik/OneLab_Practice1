package com.OneLab.Practice1.service;

import com.OneLab.Practice1.model.Customer;
import com.OneLab.Practice1.model.Order;
import com.OneLab.Practice1.repository.CustomerRepository;
import com.OneLab.Practice1.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import jakarta.persistence.EntityNotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private CustomerService customerService;

    private Customer customer;
    private List<Order> orders;

    @BeforeEach
    void setUp() {
        customer = Customer.builder()
                .id(1L)
                .name("Marlen")
                .email("marlen@gmail.com")
                .password("1111")
                .build();

        Order order1 = Order.builder()
                .id(1L)
                .orderNumber("123")
                .customer(customer)
                .build();

        Order order2 = Order.builder()
                .id(2L)
                .orderNumber("124")
                .customer(customer)
                .build();

        orders = Arrays.asList(order1, order2);
    }

    @Test
    void testCreateCustomer() {
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        Customer savedCustomer = customerService.createCustomer(customer);

        assertNotNull(savedCustomer);
        assertEquals(customer.getId(), savedCustomer.getId());
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void testFindCustomerById_CustomerExists() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        Customer foundCustomer = customerService.findCustomerById(1L);

        assertNotNull(foundCustomer);
        assertEquals(customer.getId(), foundCustomer.getId());
        verify(customerRepository, times(1)).findById(1L);
    }

    @Test
    void testFindCustomerById_CustomerDoesNotExist() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> customerService.findCustomerById(1L));
        verify(customerRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteCustomer() {
        doNothing().when(customerRepository).deleteById(1L);

        customerService.deleteCustomer(1L);

        verify(customerRepository, times(1)).deleteById(1L);
    }

    @Test
    void testFindOrdersByCustomerEmail() {
        when(orderRepository.findOrdersByCustomerEmail("marlen@gmail.com")).thenReturn(orders);

        List<Order> customerOrders = customerService.findOrdersByCustomerEmail("marlen@gmail.com");

        assertNotNull(customerOrders);
        assertEquals(2, customerOrders.size());
        verify(orderRepository, times(1)).findOrdersByCustomerEmail("marlen@gmail.com");
    }
}
