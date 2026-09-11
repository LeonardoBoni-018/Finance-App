package com.financeapp.demo.service;

import com.financeapp.demo.dto.request.ReminderRequestDTO;
import com.financeapp.demo.dto.response.ReminderResponseDTO;

import java.util.List;

public interface ReminderService {

    ReminderResponseDTO create(Long userId, ReminderRequestDTO dto);

    List<ReminderResponseDTO> findAllByUser(Long userId);

    ReminderResponseDTO markAsPaid(Long userId, Long reminderId);
}
