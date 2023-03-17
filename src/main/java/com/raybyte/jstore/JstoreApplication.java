package com.raybyte.jstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class JstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(JstoreApplication.class, args);
	}

}
