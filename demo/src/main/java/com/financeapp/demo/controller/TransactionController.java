package com.financeapp.demo.controller;

import com.financeapp.demo.dto.request.TransactionRequestDTO;
import com.financeapp.demo.dto.response.TransactionResponseDTO;
import com.financeapp.demo.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/users/{userId}")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/transactions")
    public ResponseEntity<TransactionResponseDTO> create(
            @PathVariable Long userId,
            @Valid @RequestBody TransactionRequestDTO dto) {
        TransactionResponseDTO created = transactionService.create(userId, dto);
        return ResponseEntity.created(URI.create("/api/users/" + userId + "/transactions/" + created.id())).body(created);
    }

    @GetMapping("/accounts/{accountId}/transactions")
    public ResponseEntity<Page<TransactionResponseDTO>> findAllByAccount(
            @PathVariable Long userId,
            @PathVariable Long accountId,
            @PageableDefault(size = 20, sort = "transactionDate", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(transactionService.findAllByAccount(userId, accountId, pageable));
    }
}
