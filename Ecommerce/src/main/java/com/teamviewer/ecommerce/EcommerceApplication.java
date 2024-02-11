package com.teamviewer.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * 
 * @author Paul Ngouabeu
 * This is main class for the application.
 *
 */
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "E-Commerce Platform API", version = "1.0.0", description = "RESTful API for a simple e-commerce platform"))
public class EcommerceApplication {
	
	/**
	 * This method severs as entry point for application.
	 * @param args
	 */
	public static void main(final String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

}
