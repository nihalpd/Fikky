package com.fikky;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class FikkyApplication {

	public static void main(String[] args) {
		SpringApplication.run(FikkyApplication.class, args);
	}
}
