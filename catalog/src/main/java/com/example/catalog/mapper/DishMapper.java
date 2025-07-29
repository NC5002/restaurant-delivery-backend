package com.example.catalog.mapper; // <--- ¡IMPORTANTE! Ajusta este paquete si el tuyo es diferente

import com.example.catalog.domain.Category; // Importa Category
import com.example.catalog.domain.Dish; // Importa Dish
import com.example.catalog.repository.CategoryRepository; // Importa CategoryRepository
import com.example.catalog.web.dto.DishDTO; // Importa DishDTO
import org.mapstruct.AfterMapping; // Para lógica después del mapeo
import org.mapstruct.Context; // Para inyectar dependencias en el mapeo
import org.mapstruct.Mapper; // Anotación de MapStruct
import org.mapstruct.MappingTarget; // Para mapear a un objeto existente

@Mapper(componentModel = "spring") // Indica que MapStruct debe generar un componente Spring (bean)
public interface DishMapper {

    // Mapea un objeto Dish a un DishDTO
    DishDTO toDto(Dish dish);

    // Mapea un DishDTO a un objeto Dish.
    // @Context CategoryRepository repo: Permite inyectar CategoryRepository para buscar la categoría.
    Dish toEntity(DishDTO dto, @Context CategoryRepository repo);

    // Este método se ejecuta DESPUÉS del mapeo principal de DishDTO a Dish.
    // Se usa para establecer la relación ManyToOne con Category usando el categoryId del DTO.
    @AfterMapping
    default void link(@MappingTarget Dish dish, DishDTO dto,
                      @Context CategoryRepository repo) {
        if (dto.getCategoryId() != null) {
            // Busca la referencia de la categoría por ID y la asigna al plato.
            // getReferenceById es eficiente porque no carga la entidad completa a menos que sea necesario.
            dish.setCategory(repo.getReferenceById(dto.getCategoryId()));
        }
    }
}