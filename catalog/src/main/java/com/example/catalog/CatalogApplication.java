package com.example.catalog; // o com.example.order para el servicio order

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient; // <-- IMPORTA ESTO

@SpringBootApplication
@EnableDiscoveryClient // <-- AÑADE ESTA ANOTACIÓN
public class CatalogApplication { // o OrderApplication

    public static void main(String[] args) {
        SpringApplication.run(CatalogApplication.class, args);
    }

}
