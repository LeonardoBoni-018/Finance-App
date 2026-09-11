package com.financeapp.demo.service.impl;

import com.financeapp.demo.dto.request.ReminderRequestDTO;
import com.financeapp.demo.dto.response.ReminderResponseDTO;
import com.financeapp.demo.entity.Reminder;
import com.financeapp.demo.entity.User;
import com.financeapp.demo.entity.enums.ReminderStatus;
import com.financeapp.demo.exception.ResourceNotFoundException;
import com.financeapp.demo.mapper.ReminderMapper;
import com.financeapp.demo.repository.ReminderRepository;
import com.financeapp.demo.repository.UserRepository;
import com.financeapp.demo.service.ReminderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReminderServiceImpl implements ReminderService {

    private final ReminderRepository reminderRepository;
    private final UserRepository userRepository;
    private final ReminderMapper reminderMapper;

    @Override
    @Transactional
    public ReminderResponseDTO create(Long userId, ReminderRequestDTO dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado: " + userId));

        Reminder reminder = reminderMapper.toEntity(dto);
        reminder.setUser(user);
        reminder.setStatus(ReminderStatus.PENDING); // todo lembrete novo nasce pendente
        reminder.setNotificationSent(false);

        Reminder saved = reminderRepository.save(reminder);
        return reminderMapper.toResponseDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReminderResponseDTO> findAllByUser(Long userId) {
        return reminderRepository.findAllByUserId(userId).stream()
                .map(reminderMapper::toResponseDTO)
                .toList();
    }

    @Override
    @Transactional
    public ReminderResponseDTO markAsPaid(Long userId, Long reminderId) {
        Reminder reminder = reminderRepository.findById(reminderId)
                .orElseThrow(() -> new ResourceNotFoundException("Lembrete não encontrado: " + reminderId));

        if (!reminder.getUser().getId().equals(userId)) {
            throw new ResourceNotFoundException("Lembrete não encontrado: " + reminderId);
        }

        reminder.setStatus(ReminderStatus.PAID);
        Reminder saved = reminderRepository.save(reminder);
        return reminderMapper.toResponseDTO(saved);
    }
}
