package com.example.catalog.domain; // <--- ¡IMPORTANTE! Ajusta este paquete si el tuyo es diferente

import jakarta.persistence.*;
import lombok.Data; // Incluye @Getter, @Setter, @ToString, @EqualsAndHashCode

@Entity // Indica que esta clase es una entidad JPA
@Table(name = "categories") // Especifica el nombre de la tabla
@Data // Genera automáticamente getters, setters, toString, equals y hashCode (Lombok)
public class Category {

    @Id // Marca este campo como la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configura la generación automática de IDs
    private Long id;

    @Column(nullable = false, unique = true, length = 50) // No nula, única, longitud máxima 50
    private String name;
}