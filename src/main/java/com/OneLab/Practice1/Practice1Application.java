package com.OneLab.Practice1;

import com.OneLab.Practice1.config.DBConfig;
import com.OneLab.Practice1.model.Customer;
import com.OneLab.Practice1.model.Order;
import com.OneLab.Practice1.service.CustomerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class Practice1Application {
	public static void main(String[] args) {
		SpringApplication.run(Practice1Application.class, args);
	}
}

