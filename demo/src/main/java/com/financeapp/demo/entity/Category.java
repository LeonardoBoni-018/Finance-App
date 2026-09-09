package com.financeapp.demo.entity;

import com.financeapp.demo.entity.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 60)
    private String name;

    // Categoria é de receita ou de despesa (ex: "Salário" x "Alimentação")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TransactionType type;

    // Nome de ícone (referência pro frontend) - opcional
    @Column(length = 50)
    private String icon;

    // Cor em hexadecimal para exibição no frontend - opcional
    @Column(length = 7)
    private String color;
}

