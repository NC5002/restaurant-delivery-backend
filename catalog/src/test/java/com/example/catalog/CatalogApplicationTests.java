package com.example.catalog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

// Con @SpringBootTest, le decimos que es una prueba de Spring.
// Con @SpringBootApplication(exclude...), le decimos que ignore la configuración
// de la base de datos para esta prueba simple, ya que no la necesita.
@SpringBootTest
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
class CatalogApplicationTests {

    @Test
    void contextLoads() {
        // Este test ahora solo verifica que los beans de Spring se pueden cargar
        // sin intentar conectarse a una base de datos.
    }
}