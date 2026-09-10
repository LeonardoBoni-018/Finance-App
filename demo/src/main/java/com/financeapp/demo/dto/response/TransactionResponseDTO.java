package com.financeapp.demo.dto.response;


import com.financeapp.demo.entity.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record TransactionResponseDTO(
        Long id,
        String description,
        BigDecimal amount,
        TransactionType type,
        LocalDate transactionDate,
        LocalDateTime createdAt,
        Long accountId,
        CategoryResponseDTO category
) {
}
