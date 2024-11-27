package org.example.smallbankexample.domain.ports.in;

import org.example.smallbankexample.domain.models.Transaction;
import org.example.smallbankexample.domain.models.Wallet;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public interface TransferMoneyUseCase {
    Optional<Transaction> transferMoney(Wallet originWallet, Wallet destinationWallet, BigDecimal amount, LocalDateTime timeStamp);
}
