package com.financeapp.demo.mapper;

import com.financeapp.demo.dto.request.TransactionRequestDTO;
import com.financeapp.demo.dto.response.TransactionResponseDTO;
import com.financeapp.demo.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CategoryMapper.class)
public interface TransactionMapper {

    // account e category são resolvidos no service a partir dos ids (accountId/categoryId)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "account", ignore = true)
    @Mapping(target = "category", ignore = true)
    Transaction toEntity(TransactionRequestDTO dto);

    @Mapping(target = "accountId", source = "account.id")
    @Mapping(target = "category", source = "category")
    TransactionResponseDTO toResponseDTO(Transaction entity);
}

