package com.example.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.math.BigDecimal;

// name="CATALOG" debe coincidir con el nombre del servicio en Eureka
// path="/catalog/api/v1" es el prefijo de la API del otro servicio
@FeignClient(name = "CATALOG", path = "/catalog/api/v1")
public interface CatalogClient {

    // La ruta aquí se combina con el path de arriba: /catalog/api/v1/dishes/{id}/availability
    @GetMapping("/internal/dishes/{id}/availability")
    DishAvailability checkDishAvailability(@PathVariable Long id);

    // Este 'record' es un DTO simple para recibir la respuesta.
    // Define la estructura de datos que esperamos del servicio catalog.
    record DishAvailability(Long dishId, boolean available, BigDecimal price) {}
}