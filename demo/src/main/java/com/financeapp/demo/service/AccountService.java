package com.financeapp.demo.service;

import com.financeapp.demo.dto.request.AccountRequestDTO;
import com.financeapp.demo.dto.response.AccountResponseDTO;
import java.util.List;

public interface AccountService {

    AccountResponseDTO create(Long userId, AccountRequestDTO dto);

    List<AccountResponseDTO> findAllByUser(Long userId);

    AccountResponseDTO findByIdAndUser(Long accountId, Long userId);
}
