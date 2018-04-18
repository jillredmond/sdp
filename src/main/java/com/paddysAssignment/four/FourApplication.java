package com.paddysAssignment.four;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FourApplication {

	public static void main(String[] args) {
		SpringApplication.run(FourApplication.class, args);
	}
}
