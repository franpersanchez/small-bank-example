package org.example.smallbankexample.application.services;

import org.example.smallbankexample.application.mapper.UserMapper;
import org.example.smallbankexample.application.mapper.WalletMapper;
import org.example.smallbankexample.application.usecases.WalletService;
import org.example.smallbankexample.domain.models.User;
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
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WalletManagementService implements WalletService {

    private final WalletRepositoryPort walletRepositoryPort;
    private final WalletMapper walletMapper;
    private final UserRepositoryPort userRepositoryPort;
    private final UserMapper userMapper;

    public WalletManagementService(WalletRepositoryPort walletRepositoryPort, WalletMapper walletMapper, UserRepositoryPort userRepositoryPort, UserMapper userMapper) {
        this.walletRepositoryPort = walletRepositoryPort;
        this.walletMapper = walletMapper;
        this.userMapper = userMapper;
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public WalletDto createWallet(WalletRequest walletRequest){
        Wallet wallet = WalletMapper.toDomain(walletRequest);
        walletRepositoryPort.save(wallet);
        return WalletMapper.toDto(wallet);
    }

    @Override
    public Map<String, LocalDateTime> depositMoney(Wallet destinationWallet, BigDecimal amount, LocalDateTime timestamp) {
        return null;
    }

    @Override
    public WalletDto getBalanceById(long id) {
        Wallet w = walletRepositoryPort.findWalletById(id);
        if(w != null){
            return WalletMapper.toDto(w);
        }
        throw new UserException(HttpStatus.BAD_REQUEST,
                String.format(WalletConstant.WALLET_DOES_NOT_EXIST, w.getName()));
    }

    @Override
    public Map<String, LocalDateTime> transferMoney(Wallet originWallet, Wallet destinationWallet, BigDecimal amount, LocalDateTime timeStamp) {
        return null;
    }

    @Override
    public List<WalletDto> getAllWallets() {
        var wallets = walletRepositoryPort.findAllWallets();
        return wallets.stream().map(WalletMapper::toDto).collect(Collectors.toList());
    }
}
