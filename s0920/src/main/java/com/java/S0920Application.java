package com.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class S0920Application {

	public static void main(String[] args) {
		SpringApplication.run(S0920Application.class, args);
	}

}
