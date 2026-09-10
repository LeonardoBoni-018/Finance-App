package com.financeapp.demo.mapper;

import com.financeapp.demo.dto.request.CategoryRequestDTO;
import com.financeapp.demo.dto.response.CategoryResponseDTO;
import com.financeapp.demo.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "id", ignore = true)
    Category toEntity(CategoryRequestDTO dto);

    CategoryResponseDTO toResponseDTO(Category entity);
}
