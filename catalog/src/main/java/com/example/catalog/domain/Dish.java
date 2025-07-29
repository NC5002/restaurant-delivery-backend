package com.example.catalog.domain; // <--- ¡IMPORTANTE! Ajusta este paquete si el tuyo es diferente

import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.*;

@Entity // Indica que esta clase es una entidad JPA y se mapeará a una tabla de base de datos
@Table(name = "dishes") // Especifica el nombre de la tabla si es diferente al nombre de la clase
@Getter // Genera automáticamente los métodos getter para todos los campos (Lombok)
@Setter // Genera automáticamente los métodos setter para todos los campos (Lombok)
@Builder // Permite construir objetos de forma fluida (Lombok)
@NoArgsConstructor // Genera un constructor sin argumentos (Lombok)
@AllArgsConstructor // Genera un constructor con todos los argumentos (Lombok)
public class Dish {

    @Id // Marca este campo como la clave primaria de la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configura la generación automática de IDs (autoincremental)
    private Long id;

    @Column(nullable = false, length = 120) // Configura la columna: no nula, longitud máxima 120
    private String name;

    private String description; // Se mapea a una columna 'description' por defecto
    private BigDecimal price; // Se mapea a una columna 'price' por defecto
    private Boolean available = true; // Se mapea a una columna 'available', con valor por defecto 'true'

    @ManyToOne(fetch = FetchType.LAZY) // Define una relación muchos a uno con la entidad Category
    @JoinColumn(name = "category_id") // Especifica la columna de clave foránea en la tabla 'dishes'
    private Category category;
}