package com.financeapp.demo.service.impl;

import com.financeapp.demo.dto.request.AccountRequestDTO;
import com.financeapp.demo.dto.response.AccountResponseDTO;
import com.financeapp.demo.entity.Account;
import com.financeapp.demo.entity.User;
import com.financeapp.demo.exception.ResourceNotFoundException;
import com.financeapp.demo.mapper.AccountMapper;
import com.financeapp.demo.repository.AccountRepository;
import com.financeapp.demo.repository.UserRepository;
import com.financeapp.demo.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final AccountMapper accountMapper;

    @Override
    @Transactional
    public AccountResponseDTO create(Long userId, AccountRequestDTO dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado: " + userId));

        Account account = accountMapper.toEntity(dto);
        account.setUser(user);
        account.setBalance(BigDecimal.ZERO); // toda conta nasce com saldo zero

        Account saved = accountRepository.save(account);
        return accountMapper.toResponseDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountResponseDTO> findAllByUser(Long userId) {
        return accountRepository.findAllByUserId(userId).stream()
                .map(accountMapper::toResponseDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AccountResponseDTO findByIdAndUser(Long accountId, Long userId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada: " + accountId));

        // Garante que o usuário só veja as próprias contas, mesmo sabendo o id de outra
        if (!account.getUser().getId().equals(userId)) {
            throw new ResourceNotFoundException("Conta não encontrada: " + accountId);
        }

        return accountMapper.toResponseDTO(account);
    }
}
