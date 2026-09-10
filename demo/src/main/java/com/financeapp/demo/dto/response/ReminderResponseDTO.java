package com.financeapp.demo.dto.response;

import com.financeapp.demo.entity.enums.ReminderStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record ReminderResponseDTO(
        Long id,
        String title,
        String description,
        BigDecimal amount,
        LocalDate dueDate,
        ReminderStatus status,
        boolean notificationSent,
        LocalDateTime createdAt
) {
}
