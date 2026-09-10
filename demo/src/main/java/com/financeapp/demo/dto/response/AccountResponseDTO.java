package com.financeapp.demo.dto.response;

import com.financeapp.demo.entity.enums.AccountType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AccountResponseDTO(
        Long id,
        String name,
        AccountType type,
        BigDecimal balance,
        LocalDateTime createdAt
) {
}