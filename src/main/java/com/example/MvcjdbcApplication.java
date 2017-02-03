package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.example.storage.StorageProperties;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(StorageProperties.class)
public class MvcjdbcApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(MvcjdbcApplication.class);
	}
}
