package com.onlinebankingserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OnlinebankingserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlinebankingserverApplication.class, args);
	}

}
