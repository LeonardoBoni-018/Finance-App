package com.financeapp.demo.dto.request;

import com.financeapp.demo.entity.enums.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionRequestDTO(

        @NotBlank(message = "Descrição é obrigatória")
        @Size(max = 150, message = "Descrição deve ter no máximo 150 caracteres")
        String description,

        @NotNull(message = "Valor é obrigatório")
        @Positive(message = "Valor deve ser positivo")
        BigDecimal amount,

        @NotNull(message = "Tipo da transação é obrigatório")
        TransactionType type,

        @NotNull(message = "Data da transação é obrigatória")
        LocalDate transactionDate,

        @NotNull(message = "Conta é obrigatória")
        Long accountId,

        Long categoryId
) {
}
