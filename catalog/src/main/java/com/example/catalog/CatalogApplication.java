package com.example.catalog; // Asegúrate de que tu paquete sea correcto

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient; // <-- ¡Esta es la importante!

@SpringBootApplication
@EnableDiscoveryClient // <-- Asegúrate de que esta anotación esté presente
public class CatalogApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatalogApplication.class, args);
    }
}