package com.example.catalog.web; // <--- ¡IMPORTANTE! Ajusta este paquete si el tuyo es diferente

import com.example.catalog.service.DishService; // Importa DishService
import com.example.catalog.web.dto.DishDTO; // Importa DishDTO
import jakarta.validation.Valid; // Para validación de objetos DTO
import lombok.RequiredArgsConstructor; // Para inyección de dependencias
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity; // Para construir respuestas HTTP
import org.springframework.validation.annotation.Validated; // Para validación a nivel de clase/método
import org.springframework.web.bind.annotation.*; // Anotaciones REST

@RestController // Marca esta clase como un controlador REST
@RequestMapping("/dishes") // <--- ¡CAMBIO CLAVE AQUÍ! Ahora espera solo "/dishes"
@RequiredArgsConstructor // Genera un constructor con los campos 'final'
@Validated // Habilita la validación de Spring para los métodos de este controlador
public class DishController {

    private final DishService svc; // Inyección del servicio de platos

    @GetMapping // Mapea las solicitudes GET a /dishes (relativo a la base)
    public Page<DishDTO> list(Pageable p) {
        return svc.list(p); // Llama al servicio para obtener la lista de platos paginada
    }

    @PostMapping // Mapea las solicitudes POST a /dishes (relativo a la base)
    public ResponseEntity<DishDTO> create(
            @Valid @RequestBody DishDTO dto) { // Recibe un DishDTO validado en el cuerpo de la petición
        // Guarda el nuevo plato y devuelve una respuesta 201 Created con el plato guardado
        return ResponseEntity.status(201).body(svc.save(dto));
    }
}