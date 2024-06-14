package com.springsqllab.springsqllab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class SpringsqllabApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringsqllabApplication.class, args);
	}

}
