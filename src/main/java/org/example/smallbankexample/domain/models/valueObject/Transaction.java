package org.example.smallbankexample.domain.models.valueObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public final class Transaction {
    private Long id;
    private String description;
    private BigDecimal amount;
    private LocalDateTime timestamp;
    private TransactionType transactionType;

    public Transaction(Long id, String description, TransactionType transactionType, BigDecimal amount, LocalDateTime timestamp) {
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
