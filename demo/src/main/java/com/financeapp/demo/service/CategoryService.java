package com.financeapp.demo.service;

import com.financeapp.demo.dto.request.CategoryRequestDTO;
import com.financeapp.demo.dto.response.CategoryResponseDTO;
import com.financeapp.demo.entity.enums.TransactionType;

import java.util.List;

public interface CategoryService {

    CategoryResponseDTO create(CategoryRequestDTO dto);

    List<CategoryResponseDTO> findAll();

    List<CategoryResponseDTO> findAllByType(TransactionType type);
}
