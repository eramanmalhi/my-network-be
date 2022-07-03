package com.mynetwork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MyNetworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyNetworkApplication.class, args);
	}

}
