package com.financeapp.demo.repository;

import com.financeapp.demo.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAllByUserId(Long userId);

    boolean existsByIdAndUserId(Long accountId, Long userId);
}
