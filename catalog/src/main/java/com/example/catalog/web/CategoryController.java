package com.example.catalog.web;

import com.example.catalog.service.CategoryService;
import com.example.catalog.web.dto.CategoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories") // El endpoint será /categories
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService svc;

    @PostMapping
    public ResponseEntity<CategoryDTO> create(@RequestBody CategoryDTO dto) {
        return ResponseEntity.status(201).body(svc.save(dto));
    }

    @GetMapping
    public List<CategoryDTO> listAll() {
        return svc.listAll();
    }
}