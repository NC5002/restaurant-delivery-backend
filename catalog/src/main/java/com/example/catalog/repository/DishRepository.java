package com.example.catalog.repository; // <--- ¡IMPORTANTE! Ajusta este paquete si el tuyo es diferente

import com.example.catalog.domain.Dish; // Importa la entidad Dish
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository; // Interfaz base de Spring Data JPA

// JpaRepository<TipoDeEntidad, TipoDeIdDeEntidad>
public interface DishRepository extends JpaRepository<Dish, Long> {

    // Método personalizado para encontrar platos disponibles, con paginación
    Page<Dish> findByAvailableTrue(Pageable pageable);
}