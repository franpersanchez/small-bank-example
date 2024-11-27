package org.example.smallbankexample.domain.models;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public final class Transaction {
    private final UUID id;
    private String description;
    private BigDecimal amount;
    private LocalDateTime timestamp;
    private TransactionType transactionType;

    public Transaction(UUID id, String description, TransactionType transactionType, BigDecimal amount, LocalDateTime timestamp) {
        this.id = id;
        this.description = description;
        this.transactionType = transactionType;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public enum TransactionType {
        DEPOSIT,
        WITHDRAWAL
    }

}
