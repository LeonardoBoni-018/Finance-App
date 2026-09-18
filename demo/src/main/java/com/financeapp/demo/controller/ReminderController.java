package com.financeapp.demo.controller;

import com.financeapp.demo.dto.request.ReminderRequestDTO;
import com.financeapp.demo.dto.response.ReminderResponseDTO;
import com.financeapp.demo.service.ReminderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/reminders")
@RequiredArgsConstructor
public class ReminderController {

    private final ReminderService reminderService;

    @PostMapping
    public ResponseEntity<ReminderResponseDTO> create(
            @PathVariable Long userId,
            @Valid @RequestBody ReminderRequestDTO dto) {
        ReminderResponseDTO created = reminderService.create(userId, dto);
        return ResponseEntity
                .created(URI.create("/api/users/" + userId + "/reminders/" + created.id()))
                .body(created);
    }

    @GetMapping
    public ResponseEntity<List<ReminderResponseDTO>> findAll(@PathVariable Long userId) {
        return ResponseEntity.ok(reminderService.findAllByUser(userId));
    }

    @PatchMapping("/{reminderId}/pay")
    public ResponseEntity<ReminderResponseDTO> markAsPaid(
            @PathVariable Long userId,
            @PathVariable Long reminderId) {
        return ResponseEntity.ok(reminderService.markAsPaid(userId, reminderId));
    }
}
