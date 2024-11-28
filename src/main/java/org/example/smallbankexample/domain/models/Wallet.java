package org.example.smallbankexample.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.smallbankexample.domain.models.valueObject.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public final class Wallet {
    private Long id;
    private Long userId;
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
