package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MvcjdbcApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(MvcjdbcApplication.class);
	}
}
