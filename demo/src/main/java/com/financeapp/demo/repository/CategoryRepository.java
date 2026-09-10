package com.financeapp.demo.repository;

import com.financeapp.demo.entity.Category;
import com.financeapp.demo.entity.enums.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByType(TransactionType type);
}
