package com.example.catalog.web.internal;

import com.example.catalog.domain.Dish;
import com.example.catalog.repository.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/internal/dishes") // El prefijo para este controlador
@RequiredArgsConstructor
public class InternalDishController {

    private final DishRepository repo;

    // Este DTO es la respuesta que enviaremos al servicio 'order'
    public record DishAvailability(Long dishId, boolean available, BigDecimal price) {}

    @GetMapping("/{id}/availability")
    public ResponseEntity<DishAvailability> checkDishAvailability(@PathVariable Long id) {
        return repo.findById(id)
            .map(dish -> {
                // Si el platillo existe, crea el DTO de respuesta
                var availability = new DishAvailability(
                    dish.getId(),
                    dish.getAvailable(),
                    dish.getPrice()
                );
                return ResponseEntity.ok(availability);
            })
            // Si no se encuentra el platillo, devuelve un 404 Not Found
            .orElse(ResponseEntity.notFound().build());
    }
}