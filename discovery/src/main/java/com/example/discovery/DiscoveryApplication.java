package com.example.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer; // <-- ASEGÚRATE DE QUE ESTÉ

@SpringBootApplication
@EnableEurekaServer // <-- ASEGÚRATE DE QUE ESTÉ
public class DiscoveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryApplication.class, args);
    }

}