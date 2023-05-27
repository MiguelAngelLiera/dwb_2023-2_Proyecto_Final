package com.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Punto de entrada principal de la aplicación Customer.
 */
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class CustomerApplication {

	/**
	 * Método principal que inicia la aplicación Spring Boot.
	 *
	 * @param args Los argumentos de línea de comandos
	 */
	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}

}
