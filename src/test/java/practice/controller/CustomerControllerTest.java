package practice.controller;

import practice.model.Customer;
import practice.model.Order;
import practice.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createCustomer_ShouldReturnCreatedCustomer() throws Exception {
        Customer customer = Customer.builder()
                .id(1L)
                .name("John Doe")
                .email("johndoe@example.com")
                .build();

        Mockito.when(customerService.createCustomer(Mockito.any(Customer.class))).thenReturn(customer);

        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("johndoe@example.com"));
    }


    @Test
    void findCustomerById_ShouldReturnCustomer() throws Exception {
        Customer customer = Customer.builder()
                .id(1L)
                .name("John Doe")
                .email("johndoe@example.com")
                .build();

        Mockito.when(customerService.findCustomerById(1L)).thenReturn(customer);

        mockMvc.perform(get("/customers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("johndoe@example.com"));
    }


    @Test
    void deleteCustomer_ShouldReturnNoContent() throws Exception {
        Mockito.doNothing().when(customerService).deleteCustomer(1L);

        mockMvc.perform(delete("/customers/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void findOrdersByCustomerEmail_ShouldReturnOrders() throws Exception {
        Order order = Order.builder()
                .id(1L)
                .orderNumber("Order1")
                .build();

        Mockito.when(customerService.findOrdersByCustomerEmail("johndoe@example.com"))
                .thenReturn(Collections.singletonList(order));

        mockMvc.perform(get("/customers/orders").param("email", "johndoe@example.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Order1"));
    }

}
