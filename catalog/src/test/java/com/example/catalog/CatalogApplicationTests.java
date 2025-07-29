package com.example.catalog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

// CAMBIO: Excluimos la configuración de la base de datos usando una propiedad DENTRO de @SpringBootTest
@SpringBootTest(properties = "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration")
class CatalogApplicationTests {

    @Test
    void contextLoads() {
    }
}