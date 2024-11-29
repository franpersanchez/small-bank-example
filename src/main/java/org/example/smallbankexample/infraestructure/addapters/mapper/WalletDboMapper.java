package org.example.smallbankexample.infraestructure.addapters.mapper;

import org.example.smallbankexample.domain.models.Transaction;
import org.example.smallbankexample.domain.models.Wallet;
import org.example.smallbankexample.infraestructure.addapters.entities.TransactionEntity;
import org.example.smallbankexample.infraestructure.addapters.entities.WalletEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WalletDboMapper {

    public static WalletEntity toDbo(Wallet wallet) {
        if (wallet == null) {
            return null;
        }
        return new WalletEntity(
                wallet.getId(),
                wallet.getName(),
                wallet.getBalance(),
                null,
                null
        );
    }

      public Wallet toDomain(WalletEntity walletEntity) {
        if (walletEntity == null) {
            return null;
        }
        List<Transaction> transactions = walletEntity.getTransactions() != null
                ? walletEntity.getTransactions().stream()
                .map(this::toDomain)
                .collect(Collectors.toList())
                : null;

        return new Wallet(
                walletEntity.getId(),
                walletEntity.getName(),
                walletEntity.getBalance(),
                transactions
        );
    }

    private Transaction toDomain(TransactionEntity transactionEntity) {
        if (transactionEntity == null) {
            return null;
        }

        return new Transaction(
                transactionEntity.getId(),
                transactionEntity.getAmount(),
                transactionEntity.getDescription(),
                transactionEntity.getTransactionDate()
        );
    }
}
