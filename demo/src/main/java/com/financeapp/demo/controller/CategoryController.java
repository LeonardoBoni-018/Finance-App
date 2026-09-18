package com.financeapp.demo.controller;

import com.financeapp.demo.dto.request.CategoryRequestDTO;
import com.financeapp.demo.dto.response.CategoryResponseDTO;
import com.financeapp.demo.entity.enums.TransactionType;
import com.financeapp.demo.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> create(@Valid @RequestBody CategoryRequestDTO dto) {
        CategoryResponseDTO created = categoryService.create(dto);
        return ResponseEntity.created(URI.create("/api/categories/" + created.id())).body(created);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> findAll(
            @RequestParam(required = false) TransactionType type) {
        List<CategoryResponseDTO> categories = (type != null)
                ? categoryService.findAllByType(type)
                : categoryService.findAll();
        return ResponseEntity.ok(categories);
    }
}
