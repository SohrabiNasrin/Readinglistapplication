package com.nasrinsohrabi.readinglist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient


public class ReadingListApplication {

	public static void main(String[] args) {

		SpringApplication.run(ReadingListApplication.class, args);
	}

}
