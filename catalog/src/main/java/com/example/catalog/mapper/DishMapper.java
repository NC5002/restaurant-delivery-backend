package com.example.catalog.mapper;

import com.example.catalog.domain.Category;
import com.example.catalog.domain.Dish;
import com.example.catalog.repository.CategoryRepository;
import com.example.catalog.web.dto.DishDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping; // <-- CAMBIO: Añade esta importación
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DishMapper {

    // Este método necesita una regla para mapear category.id a categoryId
    @Mapping(source = "category.id", target = "categoryId")
    DishDTO toDto(Dish dish);

    // CAMBIO: Añade esta anotación para que el campo 'available' sea siempre 'true'
    @Mapping(target = "available", constant = "true")
    Dish toEntity(DishDTO dto, @Context CategoryRepository repo);

    @AfterMapping
    default void link(@MappingTarget Dish dish, DishDTO dto,
                      @Context CategoryRepository repo) {
        if (dto.getCategoryId() != null) {
            dish.setCategory(repo.getReferenceById(dto.getCategoryId()));
        }
    }
}