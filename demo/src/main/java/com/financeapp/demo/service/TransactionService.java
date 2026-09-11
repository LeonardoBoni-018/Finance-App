package com.financeapp.demo.service;

import com.financeapp.demo.dto.request.TransactionRequestDTO;
import com.financeapp.demo.dto.response.TransactionResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransactionService {

    TransactionResponseDTO create(Long userId, TransactionRequestDTO dto);

    Page<TransactionResponseDTO> findAllByAccount(Long userId, Long accountId, Pageable pageable);
}
