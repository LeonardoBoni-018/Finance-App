package com.financeapp.demo.service.impl;

import com.financeapp.demo.dto.request.CategoryRequestDTO;
import com.financeapp.demo.dto.response.CategoryResponseDTO;
import com.financeapp.demo.entity.Category;
import com.financeapp.demo.entity.enums.TransactionType;
import com.financeapp.demo.mapper.CategoryMapper;
import com.financeapp.demo.repository.CategoryRepository;
import com.financeapp.demo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    @Transactional
    public CategoryResponseDTO create(CategoryRequestDTO dto) {
        Category category = categoryMapper.toEntity(dto);
        Category saved = categoryRepository.save(category);
        return categoryMapper.toResponseDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponseDTO> findAll() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toResponseDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponseDTO> findAllByType(TransactionType type) {
        return categoryRepository.findAllByType(type).stream()
                .map(categoryMapper::toResponseDTO)
                .toList();
    }
}
