package com.example.order; // <--- AJUSTA ESTO A TU PAQUETE REAL

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers // Habilita Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderIntegrationTest {

    // Esto levanta un contenedor MySQL para las pruebas
    @Container
    static MySQLContainer<?> mysqlContainer = new MySQLContainer<>(DockerImageName.parse("mysql:8"))
        .withDatabaseName("restaurant_delivery_test_db") // Base de datos para pruebas
        .withUsername("testuser")
        .withPassword("testpassword"); // Contraseña para pruebas

    // Esto le dice a Spring dónde está la base de datos de prueba
    @DynamicPropertySource
    static void setDatasourceProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mysqlContainer::getUsername);
        registry.add("spring.datasource.password", mysqlContainer::getPassword);
    }

    @Test
    void contextLoads() {
        // Esta prueba solo verifica que la aplicación Spring puede arrancar
        // y conectarse a la base de datos de Testcontainers.
        assertThat(true).isTrue();
    }

    // Aquí irían tus pruebas reales para el servicio de órdenes
    // Por ejemplo, probar guardar y leer pedidos
}