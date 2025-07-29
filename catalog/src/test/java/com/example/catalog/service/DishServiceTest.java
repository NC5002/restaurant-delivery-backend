package com.example.catalog.service;

import com.example.catalog.domain.Dish;
import com.example.catalog.mapper.DishMapper;
import com.example.catalog.repository.CategoryRepository;
import com.example.catalog.repository.DishRepository;
import com.example.catalog.web.dto.DishDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DishServiceTest {

    // Creamos mocks para TODAS las dependencias del servicio
    @Mock
    private DishRepository dishRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private DishMapper dishMapper;

    // Inyectamos los mocks de arriba en una instancia real de DishService
    @InjectMocks
    private DishService dishService;

    @Test
    void list_dishes_successfully() {
        // 1. Arrange (Preparar)
        // Creamos un objeto Dish de mentira que el repositorio debería devolver
        Dish fakeDish = new Dish();
        Page<Dish> pageOfDishes = new PageImpl<>(List.of(fakeDish));

        // Configuramos el comportamiento de los mocks
        // Cuando se llame a findByAvailableTrue, devuelve nuestra página de platillos falsos
        when(dishRepository.findByAvailableTrue(any(Pageable.class))).thenReturn(pageOfDishes);
        // Cuando se llame al mapeador, devuelve un DTO vacío (no nos importa el contenido)
        when(dishMapper.toDto(any(Dish.class))).thenReturn(DishDTO.builder().build());

        // 2. Act (Actuar)
        // Llamamos al método que queremos probar
        Page<DishDTO> result = dishService.list(Pageable.unpaged());

        // 3. Assert (Verificar)
        // Verificamos que los resultados son los esperados
        assertThat(result).isNotNull();
        assertThat(result.getTotalElements()).isEqualTo(1);
    }

    @Test
    void save_dish_successfully() {
        // 1. Arrange (Preparar)
        DishDTO dishToSave = DishDTO.builder().name("New Dish").categoryId(1L).build();
        Dish dishEntity = new Dish(); // Entidad antes de guardar
        Dish savedDishEntity = new Dish(); // Entidad después de guardar (con ID)
        savedDishEntity.setId(1L);

        // Configuramos el comportamiento de los mocks
        when(dishMapper.toEntity(dishToSave, categoryRepository)).thenReturn(dishEntity);
        when(dishRepository.save(dishEntity)).thenReturn(savedDishEntity);
        when(dishMapper.toDto(savedDishEntity)).thenReturn(DishDTO.builder().id(1L).name("New Dish").build());

        // 2. Act (Actuar)
        DishDTO result = dishService.save(dishToSave);

        // 3. Assert (Verificar)
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo("New Dish");
    }
}