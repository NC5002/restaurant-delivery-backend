package com.example.catalog.web;

import com.example.catalog.AbstractIntegrationTest;
import com.example.catalog.domain.Category;
import com.example.catalog.repository.CategoryRepository;
import com.example.catalog.repository.DishRepository;
import com.example.catalog.web.dto.DishDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class DishControllerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private DishRepository dishRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    private Category savedCategory;

    @BeforeEach
    void setUp() {
        dishRepository.deleteAll();
        categoryRepository.deleteAll();
        Category category = new Category();
        category.setName("Pizzas de Prueba");
        savedCategory = categoryRepository.save(category);
    }

    @Test
    void should_create_dish_and_then_list_it() throws Exception {
        DishDTO newDish = DishDTO.builder()
                .name("Pizza Test")
                .description("Descripción de prueba")
                .price(new BigDecimal("15.99"))
                .categoryId(savedCategory.getId())
                .build();

        // CAMBIO: Se quita el prefijo /api/v1 de la ruta
        mockMvc.perform(post("/dishes") 
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newDish)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Pizza Test"));

        // CAMBIO: Se quita el prefijo /api/v1 de la ruta
        mockMvc.perform(get("/dishes") 
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("Pizza Test"));
    }
}