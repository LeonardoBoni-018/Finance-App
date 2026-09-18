package com.financeapp.demo.controller;

import com.financeapp.demo.dto.request.AccountRequestDTO;
import com.financeapp.demo.dto.response.AccountResponseDTO;
import com.financeapp.demo.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountResponseDTO> create(
            @PathVariable Long userId,
            @Valid @RequestBody AccountRequestDTO dto) {
        AccountResponseDTO created = accountService.create(userId, dto);
        return ResponseEntity
                .created(URI.create("/api/users/" + userId + "/accounts/" + created.id()))
                .body(created);
    }

    @GetMapping
    public ResponseEntity<List<AccountResponseDTO>> findAll(@PathVariable Long userId) {
        return ResponseEntity.ok(accountService.findAllByUser(userId));
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountResponseDTO> findById(
            @PathVariable Long userId,
            @PathVariable Long accountId) {
        return ResponseEntity.ok(accountService.findByIdAndUser(accountId, userId));
    }
}

