package org.example.smallbankexample.application.usecases;

import org.example.smallbankexample.domain.models.dto.WalletDto;
import org.example.smallbankexample.domain.models.Wallet;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

public interface WalletUseCases {

    WalletDto createWallet(Long userId);
    Optional<Map<String, LocalDateTime>> depositMoney(Wallet destinationWallet, BigDecimal amount, LocalDateTime timestamp);
    BigDecimal getBalance(Wallet wallet);
    Optional<Map<String, LocalDateTime>> transferMoney(Wallet originWallet, Wallet destinationWallet, BigDecimal amount, LocalDateTime timeStamp);
}
