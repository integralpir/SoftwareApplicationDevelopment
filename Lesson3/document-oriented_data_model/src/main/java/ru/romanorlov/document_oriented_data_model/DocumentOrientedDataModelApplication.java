package ru.romanorlov.document_oriented_data_model;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class DocumentOrientedDataModelApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocumentOrientedDataModelApplication.class, args);
	}

}
