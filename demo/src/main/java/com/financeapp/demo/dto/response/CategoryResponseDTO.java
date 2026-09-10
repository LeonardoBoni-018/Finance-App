package com.financeapp.demo.dto.response;

import com.financeapp.demo.entity.enums.TransactionType;

public record CategoryResponseDTO(
        Long id,
        String name,
        TransactionType type,
        String icon,
        String color
) {
}
