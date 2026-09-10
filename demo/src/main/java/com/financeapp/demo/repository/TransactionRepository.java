package com.financeapp.demo.repository;

import com.financeapp.demo.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Page<Transaction> findAllByAccountId(Long accountId, Pageable pageable);

    List<Transaction> findAllByAccountIdAndTransactionDateBetween(
            Long accountId, LocalDate start, LocalDate end);

    boolean existsByIdAndAccountId(Long transactionId, Long accountId);
}
