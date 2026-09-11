package com.financeapp.demo.service.impl;

import com.financeapp.demo.dto.request.TransactionRequestDTO;
import com.financeapp.demo.dto.response.TransactionResponseDTO;
import com.financeapp.demo.entity.Account;
import com.financeapp.demo.entity.Category;
import com.financeapp.demo.entity.Transaction;
import com.financeapp.demo.entity.enums.TransactionType;
import com.financeapp.demo.exception.ResourceNotFoundException;
import com.financeapp.demo.mapper.TransactionMapper;
import com.financeapp.demo.repository.AccountRepository;
import com.financeapp.demo.repository.CategoryRepository;
import com.financeapp.demo.repository.TransactionRepository;
import com.financeapp.demo.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final CategoryRepository categoryRepository;
    private final TransactionMapper transactionMapper;

    @Override
    @Transactional
    public TransactionResponseDTO create(Long userId, TransactionRequestDTO dto) {
        Account account = findOwnedAccount(dto.accountId(), userId);

        Category category = null;

        if (dto.categoryId() != null) {
            category = categoryRepository.findById(dto.categoryId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Categoria não encontrada: " + dto.categoryId()
                            ));
        }

        Transaction transaction = transactionMapper.toEntity(dto);
        transaction.setAccount(account);
        transaction.setCategory(category);

        Transaction saved = transactionRepository.save(transaction);

        applyBalanceChange(account, dto.amount(), dto.type());
        accountRepository.save(account);

        return transactionMapper.toResponseDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TransactionResponseDTO> findAllByAccount(
            Long userId,
            Long accountId,
            Pageable pageable) {

        findOwnedAccount(accountId, userId);

        return transactionRepository
                .findAllByAccountId(accountId, pageable)
                .map(transactionMapper::toResponseDTO);
    }

    private Account findOwnedAccount(Long accountId, Long userId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Conta não encontrada: " + accountId
                        ));

        if (!account.getUser().getId().equals(userId)) {
            throw new ResourceNotFoundException(
                    "Conta não encontrada: " + accountId
            );
        }

        return account;
    }

    private void applyBalanceChange(
            Account account,
            BigDecimal amount,
            TransactionType type) {

        if (type == TransactionType.INCOME) {
            account.setBalance(account.getBalance().add(amount));
        } else {
            account.setBalance(account.getBalance().subtract(amount));
        }
    }
}