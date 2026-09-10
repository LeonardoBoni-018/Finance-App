package com.financeapp.demo.mapper;

import com.financeapp.demo.dto.request.AccountRequestDTO;
import com.financeapp.demo.dto.response.AccountResponseDTO;
import com.financeapp.demo.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    // user, balance, id e createdAt são resolvidos no service (balance começa em zero, user vem do contexto autenticado)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "balance", ignore = true)
    @Mapping(target = "transactions", ignore = true)
    Account toEntity(AccountRequestDTO dto);

    AccountResponseDTO toResponseDTO(Account entity);
}

