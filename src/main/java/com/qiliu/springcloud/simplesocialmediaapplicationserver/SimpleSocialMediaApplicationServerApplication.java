package com.qiliu.springcloud.simplesocialmediaapplicationserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@SpringBootApplication
public class SimpleSocialMediaApplicationServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleSocialMediaApplicationServerApplication.class, args);
	}
}
