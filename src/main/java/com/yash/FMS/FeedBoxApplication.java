package com.yash.FMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.yash.FMS")
public class FeedBoxApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeedBoxApplication.class, args);
	}

}
