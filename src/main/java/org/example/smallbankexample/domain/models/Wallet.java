package org.example.smallbankexample.domain.models;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
public final class Wallet {
    private UUID id;
    private UUID userId;
    private BigDecimal balance;
    private List<Transaction> transactions;

    public void deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        this.balance.add(amount);
    }

    public void transferTo(Wallet destinationWallet, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Transfer amount must be greater than zero.");
        }
        if (balance.compareTo(amount) < 0) {
            throw new IllegalStateException("Not enough funds.");
        }
        balance = balance.subtract(amount);
        destinationWallet.deposit(amount);
        transactions.add(new Transaction(destinationWallet.getId(), "Transfer to" + destinationWallet.userId, Transaction.TransactionType.WITHDRAWAL, amount, LocalDateTime.now()));
    }

}
