package com.financeapp.demo.dto.request;

import com.financeapp.demo.entity.enums.AccountType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AccountRequestDTO(

        @NotBlank(message = "Nome da conta é obrigatório")
        @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
        String name,

        @NotNull(message = "Tipo da conta é obrigatório")
        AccountType type
) {
}