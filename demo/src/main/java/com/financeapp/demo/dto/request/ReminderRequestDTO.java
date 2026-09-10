package com.financeapp.demo.dto.request;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ReminderRequestDTO(

        @NotBlank(message = "Título é obrigatório")
        @Size(max = 150, message = "Título deve ter no máximo 150 caracteres")
        String title,

        @Size(max = 500, message = "Descrição deve ter no máximo 500 caracteres")
        String description,

        @Positive(message = "Valor deve ser positivo")
        BigDecimal amount,

        @NotNull(message = "Data de vencimento é obrigatória")
        @FutureOrPresent(message = "Data de vencimento não pode estar no passado")
        LocalDate dueDate
) {
}
