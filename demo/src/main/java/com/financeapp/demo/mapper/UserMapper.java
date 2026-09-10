package com.financeapp.demo.mapper;

import com.financeapp.demo.dto.request.UserRequestDTO;
import com.financeapp.demo.dto.response.UserResponseDTO;
import com.financeapp.demo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "accounts", ignore = true)
    @Mapping(target = "reminders", ignore = true)
    User toEntity(UserRequestDTO dto);

    UserResponseDTO toResponseDTO(User entity);
}
