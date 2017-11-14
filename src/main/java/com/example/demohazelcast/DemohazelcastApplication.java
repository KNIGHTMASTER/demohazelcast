package com.example.demohazelcast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@EnableJpaRepositories
@SpringBootApplication
//@EnableCaching
public class DemohazelcastApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemohazelcastApplication.class, args);
	}
}
