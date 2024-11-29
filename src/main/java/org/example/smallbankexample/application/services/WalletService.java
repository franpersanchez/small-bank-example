package org.example.smallbankexample.application.services;

import org.example.smallbankexample.application.mapper.WalletMapper;
import org.example.smallbankexample.application.usecases.WalletUseCases;
import org.example.smallbankexample.domain.models.User;
import org.example.smallbankexample.domain.models.constants.UserConstant;
import org.example.smallbankexample.domain.models.constants.WalletConstant;
import org.example.smallbankexample.domain.models.dto.WalletDto;
import org.example.smallbankexample.domain.models.Wallet;
import org.example.smallbankexample.domain.models.dto.request.WalletRequest;
import org.example.smallbankexample.domain.ports.port.UserRepositoryPort;
import org.example.smallbankexample.domain.ports.port.WalletRepositoryPort;
import org.example.smallbankexample.infraestructure.addapters.entities.UserEntity;
import org.example.smallbankexample.infraestructure.addapters.exceptions.UserException;
import org.example.smallbankexample.infraestructure.addapters.exceptions.WalletException;
import org.example.smallbankexample.infraestructure.addapters.mapper.WalletDboMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
public class WalletService implements WalletUseCases {

    private final WalletRepositoryPort walletRepositoryPort;
    private final WalletMapper walletMapper;
    private final UserRepositoryPort userRepositoryPort;

    public WalletService(WalletRepositoryPort walletRepositoryPort, WalletMapper walletMapper, UserRepositoryPort userRepositoryPort) {
        this.walletRepositoryPort = walletRepositoryPort;
        this.walletMapper = walletMapper;
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public WalletDto createWallet(WalletRequest walletRequest){
        User userEntity = userRepositoryPort.findUserById(walletRequest.getUserId());
        if(userEntity == null){
            throw new WalletException(HttpStatus.BAD_REQUEST,
                    String.format(WalletConstant.USER_DOES_NOT_EXIST));
        }
        Wallet newWallet = new Wallet(null, walletRequest.getName(), BigDecimal.ZERO, null);
        Wallet w = walletRepositoryPort.create(newWallet, userEntity);
        return walletMapper.toDto(w);
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
