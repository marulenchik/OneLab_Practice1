package practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class Practice1Application {
	public static void main(String[] args) {
		SpringApplication.run(Practice1Application.class, args);
	}
}

