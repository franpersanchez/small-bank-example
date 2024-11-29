package org.example.smallbankexample.application.usecases;

import org.example.smallbankexample.domain.models.dto.WalletDto;
import org.example.smallbankexample.domain.models.Wallet;
import org.example.smallbankexample.domain.models.dto.request.WalletRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

public interface WalletUseCases {

    WalletDto createWallet(WalletRequest walletRequest);
    Optional<Map<String, LocalDateTime>> depositMoney(Wallet destinationWallet, BigDecimal amount, LocalDateTime timestamp);
    WalletDto getBalance(WalletRequest walletRequest);
    Optional<Map<String, LocalDateTime>> transferMoney(Wallet originWallet, Wallet destinationWallet, BigDecimal amount, LocalDateTime timeStamp);
}
