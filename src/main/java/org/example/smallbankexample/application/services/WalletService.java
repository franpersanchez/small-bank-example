package org.example.smallbankexample.application.services;

import org.example.smallbankexample.application.mapper.WalletMapper;
import org.example.smallbankexample.application.usecases.WalletUseCases;
import org.example.smallbankexample.domain.models.dto.WalletDto;
import org.example.smallbankexample.domain.models.Wallet;
import org.example.smallbankexample.domain.ports.port.WalletRepositoryPort;
import org.example.smallbankexample.infraestructure.addapters.mapper.WalletDboMapper;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
public class WalletService implements WalletUseCases {

    private final WalletRepositoryPort walletRepository;
    private final WalletMapper walletMapper;

    public WalletService(WalletRepositoryPort walletRepository, WalletMapper walletMapper) {
        this.walletRepository = walletRepository;
        this.walletMapper = walletMapper;
    }

    @Override
    public WalletDto createWallet(Long userId) {

        return null;
    }

    @Override
    public Optional<Map<String, LocalDateTime>> depositMoney(Wallet destinationWallet, BigDecimal amount, LocalDateTime timestamp) {
        return Optional.empty();
    }

    @Override
    public BigDecimal getBalance(Wallet wallet) {
        return null;
    }

    @Override
    public Optional<Map<String, LocalDateTime>> transferMoney(Wallet originWallet, Wallet destinationWallet, BigDecimal amount, LocalDateTime timeStamp) {
        return Optional.empty();
    }
}
