package org.example.smallbankexample.application.usecases;

import org.example.smallbankexample.domain.models.dto.TransactionDto;
import org.example.smallbankexample.domain.models.dto.WalletDto;
import org.example.smallbankexample.domain.models.dto.request.WalletRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface WalletService {

    WalletDto createWallet(WalletRequest walletRequest);
    TransactionDto depositMoney(long  destinationWalletId, BigDecimal amount, LocalDateTime timestamp);
    WalletDto getBalanceById(long id);
    TransactionDto transferMoney(long originId, BigDecimal amount, long destinationId, LocalDateTime timeStamp);
   List<WalletDto> getAllWallets();
}
