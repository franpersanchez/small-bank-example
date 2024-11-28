package org.example.smallbankexample.application.usecases;

import org.example.smallbankexample.domain.models.Transaction;
import org.example.smallbankexample.domain.models.User;
import org.example.smallbankexample.domain.models.Wallet;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public interface WalletUseCases {

    Optional<Wallet> createWallet(User owner);
    Optional<Transaction> depositMoney(Wallet destinationWallet, BigDecimal amount, LocalDateTime timestamp);
    BigDecimal getBalance(Wallet wallet);
    Optional<Transaction> transferMoney(Wallet originWallet, Wallet destinationWallet, BigDecimal amount, LocalDateTime timeStamp);
}
