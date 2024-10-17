package com.OneLab.Practice1;

import com.OneLab.Practice1.config.AppConfig;
import com.OneLab.Practice1.model.Customer;
import com.OneLab.Practice1.model.Order;
import com.OneLab.Practice1.service.CustomerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class Practice1Application {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		CustomerService customerService = context.getBean(CustomerService.class);

		Order order1 = Order.builder()
				.id(1L)
				.totalPrice(5000)
				.build();

		Customer customer = Customer.builder()
				.id(1L)
				.name("Marlen")
				.email("marlen@gmail.com")
				.password("1234567890")
				.orders(Arrays.asList(order1))
				.build();

		customerService.addCustomer(customer);

		System.out.println("Customer: " + customerService.getCustomerById(customer.getId()));
	}
}

