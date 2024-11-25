package com.firstapplication.BankingApplication.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Setting up many-to-one relationship with Account
    @ManyToOne
    @JoinColumn(name = "account_id")  // The foreign key column in the Transaction table
    private Account account;

    private LocalDateTime transactionTime;
    private BigDecimal amount;
    private String type;

}
