package com.raybyte.jstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication(scanBasePackages = {"com.raybyte.jstore"})
@EnableWebSecurity
public class JstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(JstoreApplication.class, args);
	}

}
