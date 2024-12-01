package org.example.smallbankexample.application.services;

import org.example.smallbankexample.application.mapper.*;
import org.example.smallbankexample.application.mapper.WalletRequestMapper;
import org.example.smallbankexample.application.usecases.WalletService;
import org.example.smallbankexample.domain.models.Transaction;
import org.example.smallbankexample.domain.models.constants.WalletConstant;
import org.example.smallbankexample.domain.models.dto.TransactionDto;
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
import java.util.stream.Collectors;

@Service
public class WalletManagementService implements WalletService {

    private final WalletRepositoryPort walletRepositoryPort;
    private final WalletRequestMapper walletRequestMapper;
    private final WalletDtoMapper walletDtoMapper;
    private final UserRepositoryPort userRepositoryPort;
    private final UserRequestMapper userRequestMapper;
    private final TransactionDtoMapper transactionDtoMapper;

    public WalletManagementService(WalletRepositoryPort walletRepositoryPort, WalletRequestMapper walletRequestMapper, WalletDtoMapper walletDtoMapper, UserRepositoryPort userRepositoryPort, UserRequestMapper userRequestMapper, TransactionDtoMapper transactionDtoMapper) {
        this.walletRepositoryPort = walletRepositoryPort;
        this.walletRequestMapper = walletRequestMapper;
        this.walletDtoMapper = walletDtoMapper;
        this.userRequestMapper = userRequestMapper;
        this.userRepositoryPort = userRepositoryPort;
        this.transactionDtoMapper = transactionDtoMapper;
    }

    @Override
    public WalletDto createWallet(WalletRequest walletRequest){
        Wallet wallet = walletRequestMapper.toDomain(walletRequest);
        walletRepositoryPort.save(wallet);
        return walletDtoMapper.toDto(wallet);
    }

    @Override
    public TransactionDto depositMoney(long destinationWalletId, BigDecimal amount, LocalDateTime timestamp) {
        Wallet w = walletRepositoryPort.findWalletById(destinationWalletId);
        if(w == null){
            throw new UserException(HttpStatus.BAD_REQUEST,
                    String.format(WalletConstant.WALLET_DOES_NOT_EXIST, destinationWalletId));
        }

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription(String.format("Deposit of %s at %s", amount, timestamp));
        transaction.setTransactionDate(timestamp);

        w.getTransactions().add(transaction);

        w.setBalance(w.getBalance().add(amount));

        Wallet savedWallet = walletRepositoryPort.save(w);

        return transactionDtoMapper.toDto(transaction);
    }


    @Override
    public WalletDto getBalanceById(long id) {
        Wallet w = walletRepositoryPort.findWalletById(id);
        if(w == null){
            throw new UserException(HttpStatus.BAD_REQUEST,
                    String.format(WalletConstant.WALLET_DOES_NOT_EXIST, id));
        }
        return walletDtoMapper.toDto(w);

    }

    @Override
    public TransactionDto transferMoney(long originalWallet, BigDecimal amount, long destinationWallet, LocalDateTime timeStamp) {
        Wallet walletFrom = walletRepositoryPort.findWalletById(originalWallet);
        Wallet walletTo = walletRepositoryPort.findWalletById(destinationWallet);

        if (walletFrom == null) {
            throw new UserException(HttpStatus.BAD_REQUEST,
                    String.format(WalletConstant.WALLET_DOES_NOT_EXIST, originalWallet));
        }

        if (walletTo == null) {
            throw new UserException(HttpStatus.BAD_REQUEST,
                    String.format(WalletConstant.WALLET_DOES_NOT_EXIST, destinationWallet));
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

        return transactionDtoMapper.toDto(withdrawalTransaction);
    }

    @Override
    public List<WalletDto> getAllWallets() {
        var wallets = walletRepositoryPort.findAllWallets();
        return wallets.stream().map(walletDtoMapper::toDto).collect(Collectors.toList());
    }
}
