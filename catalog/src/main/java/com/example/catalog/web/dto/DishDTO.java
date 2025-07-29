package com.example.catalog.web.dto; // <--- ¡IMPORTANTE! Ajusta este paquete si el tuyo es diferente

import lombok.Builder; // Para construir objetos DTO
import lombok.Getter; // Para getters
import lombok.Setter; // Para setters

import java.math.BigDecimal;

@Getter // Genera getters
@Setter // Genera setters
@Builder // Permite construir objetos DishDTO
public class DishDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Long categoryId; // Para mapear la categoría por su ID
}