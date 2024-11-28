package org.example.smallbankexample.application.services;

import org.example.smallbankexample.application.usecases.WalletUseCases;
import org.example.smallbankexample.domain.models.Transaction;
import org.example.smallbankexample.domain.models.User;
import org.example.smallbankexample.domain.models.Wallet;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public class WalletService implements WalletUseCases {

    @Override
    public Optional<Wallet> createWallet(User owner) {
        return Optional.empty();
    }

    @Override
    public Optional<Transaction> depositMoney(Wallet destinationWallet, BigDecimal amount, LocalDateTime timestamp) {
        return Optional.empty();
    }

    @Override
    public BigDecimal getBalance(Wallet wallet) {
        return null;
    }

    @Override
    public Optional<Transaction> transferMoney(Wallet originWallet, Wallet destinationWallet, BigDecimal amount, LocalDateTime timeStamp) {
        return Optional.empty();
    }
}
