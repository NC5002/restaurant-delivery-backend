package com.example.catalog.service; // <--- ¡IMPORTANTE! Ajusta este paquete si el tuyo es diferente

import com.example.catalog.domain.Dish; // Importa Dish
import com.example.catalog.mapper.DishMapper; // Importa DishMapper
import com.example.catalog.repository.DishRepository; // Importa DishRepository
import com.example.catalog.web.dto.DishDTO; // Importa DishDTO
import lombok.RequiredArgsConstructor; // Genera un constructor con argumentos requeridos (Lombok)
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service; // Marca esta clase como un bean de servicio de Spring

@Service // Indica que esta clase es un componente de servicio de Spring
@RequiredArgsConstructor // Genera un constructor con los campos 'final' (para inyección de dependencias)
public class DishService {

    private final DishRepository repo; // Inyección del repositorio de platos
    private final DishMapper mapper; // Inyección del mapeador de platos

    // Lista todos los platos disponibles con paginación
    public Page<DishDTO> list(Pageable p) {
        return repo.findByAvailableTrue(p).map(mapper::toDto); // Encuentra disponibles y mapea a DTO
    }

    // Guarda un nuevo plato o actualiza uno existente
    public DishDTO save(DishDTO dto) {
        Dish dish = mapper.toEntity(dto, null); // Mapea DTO a entidad (el 'null' es para el CategoryRepository en el mapper)
        return mapper.toDto(repo.save(dish)); // Guarda en la base de datos y mapea el resultado a DTO
    }
}