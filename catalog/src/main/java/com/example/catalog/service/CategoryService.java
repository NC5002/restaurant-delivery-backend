package com.example.catalog.service;

import com.example.catalog.repository.CategoryRepository;
import com.example.catalog.domain.Category;
import com.example.catalog.mapper.CategoryMapper;
import com.example.catalog.web.dto.CategoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repo;
    private final CategoryMapper mapper;

    public CategoryDTO save(CategoryDTO dto) {
        Category category = repo.save(mapper.toEntity(dto));
        return mapper.toDto(category);
    }

    public List<CategoryDTO> listAll() {
        return repo.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }
}