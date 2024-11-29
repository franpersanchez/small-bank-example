package org.example.smallbankexample.application.services;

import org.example.smallbankexample.application.mapper.UserMapper;
import org.example.smallbankexample.application.mapper.WalletMapper;
import org.example.smallbankexample.application.usecases.WalletService;
import org.example.smallbankexample.domain.models.Transaction;
import org.example.smallbankexample.domain.models.constants.WalletConstant;
import org.example.smallbankexample.domain.models.dto.WalletDto;
import org.example.smallbankexample.domain.models.Wallet;
import org.example.smallbankexample.domain.models.dto.request.WalletRequest;
import org.example.smallbankexample.domain.ports.port.UserRepositoryPort;
import org.example.smallbankexample.domain.ports.port.WalletRepositoryPort;
import org.example.smallbankexample.infraestructure.addapters.exceptions.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
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
    public WalletDto depositMoney(long destinationWalletId, BigDecimal amount, LocalDateTime timestamp) {
        Wallet w = walletRepositoryPort.findWalletById(destinationWalletId);
        if(w == null){
            throw new UserException(HttpStatus.BAD_REQUEST,
                    String.format(WalletConstant.WALLET_DOES_NOT_EXIST, w.getName()));
        }

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription(String.format("Deposit of %s at %s", amount, timestamp));
        transaction.setTransactionDate(timestamp);

        w.getTransactions().add(transaction);

        w.setBalance(w.getBalance().add(amount));

        Wallet savedWallet = walletRepositoryPort.save(w);

        return walletMapper.toDto(savedWallet);
    }


    @Override
    public WalletDto getBalanceById(long id) {
        Wallet w = walletRepositoryPort.findWalletById(id);
        if(w == null){
            throw new UserException(HttpStatus.BAD_REQUEST,
                    String.format(WalletConstant.WALLET_DOES_NOT_EXIST, w.getName()));
        }
        return WalletMapper.toDto(w);

    }

    @Override
    public WalletDto transferMoney(long originalWallet, BigDecimal amount, long destinationWallet, LocalDateTime timeStamp) {
        Wallet walletFrom = walletRepositoryPort.findWalletById(originalWallet);
        Wallet walletTo = walletRepositoryPort.findWalletById(destinationWallet);

        if (walletFrom == null) {
            throw new UserException(HttpStatus.BAD_REQUEST,
                    String.format(WalletConstant.WALLET_DOES_NOT_EXIST, walletFrom.getName()));
        }

        if (walletTo == null) {
            throw new UserException(HttpStatus.BAD_REQUEST,
                    String.format(WalletConstant.WALLET_DOES_NOT_EXIST, walletTo.getName()));
        }

        if (walletFrom.getBalance().compareTo(amount) < 0) {
            throw new UserException(HttpStatus.BAD_REQUEST, "Insufficient balance in the source wallet.");
        }

        Transaction withdrawalTransaction = new Transaction();
        withdrawalTransaction.setAmount(amount);
        withdrawalTransaction.setDescription(String.format("Withdrawal of %s to wallet %s at %s", amount, walletTo.getName(), timeStamp));
        withdrawalTransaction.setTransactionDate(timeStamp);

        Transaction depositTransaction = new Transaction();
        depositTransaction.setAmount(amount);
        depositTransaction.setDescription(String.format("Deposit of %s from wallet %s at %s", amount, walletFrom.getName(), timeStamp));
        depositTransaction.setTransactionDate(timeStamp);

        walletFrom.getTransactions().add(withdrawalTransaction);
        walletTo.getTransactions().add(depositTransaction);

        walletFrom.setBalance(walletFrom.getBalance().subtract(amount));
        walletTo.setBalance(walletTo.getBalance().add(amount));

        walletRepositoryPort.save(walletFrom);
        walletRepositoryPort.save(walletTo);

        return walletMapper.toDto(walletTo);
    }

    @Override
    public List<WalletDto> getAllWallets() {
        var wallets = walletRepositoryPort.findAllWallets();
        return wallets.stream().map(WalletMapper::toDto).collect(Collectors.toList());
    }
}
