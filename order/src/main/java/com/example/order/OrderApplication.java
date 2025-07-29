package com.example.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient; // <-- IMPORTA ESTO

@SpringBootApplication
@EnableDiscoveryClient // <-- AÑADE ESTA ANOTACIÓN
public class OrderApplication { // o OrderApplication

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

}
