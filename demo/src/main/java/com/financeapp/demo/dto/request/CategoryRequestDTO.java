package com.financeapp.demo.dto.request;

import com.financeapp.demo.entity.enums.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CategoryRequestDTO(

        @NotBlank(message = "Nome da categoria é obrigatório")
        @Size(max = 60, message = "Nome deve ter no máximo 60 caracteres")
        String name,

        @NotNull(message = "Tipo da categoria é obrigatório")
        TransactionType type,

        String icon,

        @Pattern(regexp = "^#([A-Fa-f0-9]{6})$", message = "Cor deve estar em formato hexadecimal, ex: #FF5733")
        String color
) {
}
