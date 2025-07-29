package com.example.order; // Asegúrate que este paquete sea el correcto para tu servicio 'order'

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers(disabledWithoutDocker = true)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderIntegrationTest {

    // Definimos el contenedor MySQL. Usamos una versión específica para asegurar consistencia.
    @Container
    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0.33");

    // Este método inyecta las propiedades de la base de datos de Testcontainers en el contexto de Spring.
    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
        // Aseguramos que Hibernate cree el esquema de la base de datos en el contenedor de prueba.
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");

        // *** IMPORTANTE: Deshabilitamos el cliente Eureka durante las pruebas de integración ***
        // Esto evita que el servicio intente conectarse a un servidor Eureka que no existe en el entorno de prueba.
        registry.add("eureka.client.enabled", () -> "false");
        registry.add("spring.cloud.discovery.enabled", () -> "false");
    }

    @Test
    void contextLoads() {
        // Este test simplemente verifica que el contexto de Spring Boot se carga correctamente.
        // Con la configuración anterior, también asegura que la conexión a la base de datos de Testcontainers se establece.
    }
}