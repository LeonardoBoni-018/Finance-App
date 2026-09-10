package com.financeapp.demo.repository;

import com.financeapp.demo.entity.Reminder;
import com.financeapp.demo.entity.enums.ReminderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReminderRepository extends JpaRepository<Reminder, Long> {

    List<Reminder> findAllByUserId(Long userId);

    List<Reminder> findAllByStatusAndNotificationSentFalseAndDueDateLessThanEqual(
            ReminderStatus status, LocalDate limitDate);
}
