package com.example.catalog.repository; // <--- ¡IMPORTANTE! Ajusta este paquete si el tuyo es diferente

import com.example.catalog.domain.Category; // Importa la entidad Category
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Spring Data JPA generará automáticamente las implementaciones CRUD
}