package com.raybyte.jstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"com.raybyte.jstore"})
public class JstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(JstoreApplication.class, args);
	}

}
