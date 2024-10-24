package ru.romanorlov.relational_data_model;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class RelationalDataModelApplication {

	public static void main(String[] args) {
		SpringApplication.run(RelationalDataModelApplication.class, args);
	}

}
