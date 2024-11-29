package org.example.smallbankexample.application.services;

import org.example.smallbankexample.application.mapper.UserMapper;
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
import org.example.smallbankexample.infraestructure.addapters.exceptions.UserException;
import org.example.smallbankexample.infraestructure.addapters.exceptions.WalletException;
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
    private final UserMapper userMapper;

    public WalletService(WalletRepositoryPort walletRepositoryPort, WalletMapper walletMapper, UserRepositoryPort userRepositoryPort, UserMapper userMapper) {
        this.walletRepositoryPort = walletRepositoryPort;
        this.walletMapper = walletMapper;
        this.userMapper = userMapper;
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public WalletDto createWallet(WalletRequest walletRequest){
        User user = userRepositoryPort.findUserById(walletRequest.getUserId());
        if(user == null){
            throw new WalletException(HttpStatus.BAD_REQUEST,
                    String.format(WalletConstant.USER_DOES_NOT_EXIST));
        }
        Wallet w = WalletMapper.toDomain(walletRequest);
        walletRepositoryPort.create(w, user);
        return WalletMapper.toDto(w);
    }

    @Override
    public Optional<Map<String, LocalDateTime>> depositMoney(Wallet destinationWallet, BigDecimal amount, LocalDateTime timestamp) {
        return Optional.empty();
    }

    @Override
    public WalletDto getBalance(WalletRequest walletRequest) {
        System.out.println("esto es getBalance");
        Wallet walletDomain = WalletMapper.toDomain(walletRequest);
        System.out.println("se v a a pasar " + walletDomain.getId().toString());
        Wallet w = walletRepositoryPort.findWalletById(walletDomain.getId());
        if(w != null){
            return WalletMapper.toDto(w);
        }
        throw new UserException(HttpStatus.BAD_REQUEST,
                String.format(WalletConstant.WALLET_DOES_NOT_EXIST, walletRequest.getName()));
    }

    @Override
    public Optional<Map<String, LocalDateTime>> transferMoney(Wallet originWallet, Wallet destinationWallet, BigDecimal amount, LocalDateTime timeStamp) {
        return Optional.empty();
    }
}
