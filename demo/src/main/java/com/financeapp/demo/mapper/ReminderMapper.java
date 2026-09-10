package com.financeapp.demo.mapper;

import com.financeapp.demo.dto.request.ReminderRequestDTO;
import com.financeapp.demo.dto.response.ReminderResponseDTO;
import com.financeapp.demo.entity.Reminder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReminderMapper {

    // user, status, notificationSent, id e createdAt têm regra própria no service
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "notificationSent", ignore = true)
    Reminder toEntity(ReminderRequestDTO dto);

    ReminderResponseDTO toResponseDTO(Reminder entity);
}
