package com.example.catalog.service;

import com.example.catalog.domain.Dish;
import com.example.catalog.mapper.DishMapper;
import com.example.catalog.repository.CategoryRepository; // <--- AÑADIR este import
import com.example.catalog.repository.DishRepository;
import com.example.catalog.web.dto.DishDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DishService {

    private final DishRepository repo;
    private final DishMapper mapper;
    private final CategoryRepository categoryRepo; // <--- AÑADIR el repositorio de categorías

    public Page<DishDTO> list(Pageable p) {
        return repo.findByAvailableTrue(p).map(mapper::toDto);
    }

    public DishDTO save(DishDTO dto) {
        // <--- CAMBIO CLAVE: Pasar el categoryRepo al mapeador
        Dish dish = mapper.toEntity(dto, categoryRepo); 
        return mapper.toDto(repo.save(dish));
    }
}