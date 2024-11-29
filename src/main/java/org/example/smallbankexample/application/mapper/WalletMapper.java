package org.example.smallbankexample.application.mapper;

import org.example.smallbankexample.domain.models.Transaction;
import org.example.smallbankexample.domain.models.Wallet;
import org.example.smallbankexample.domain.models.dto.TransactionDto;
import org.example.smallbankexample.domain.models.dto.WalletDto;
import org.example.smallbankexample.domain.models.dto.request.WalletRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class WalletMapper {

    public static Wallet toDomain(WalletRequest walletRequest) {
        if (walletRequest == null) {
            return null;
        }


        List<Transaction> transactions = new ArrayList<>();

        return new Wallet(
                walletRequest.getId(),
                walletRequest.getName(),
                walletRequest.getBalance(),
                transactions
        );
    }


    public static WalletDto toDto(Wallet wallet) {
        if (wallet == null) {
            return null;
        }


        List<TransactionDto> transactionDtos = wallet.getTransactions().stream()
                .map(t -> new TransactionDto(t.getId(), t.getAmount(), t.getDescription(), t.getTransactionDate()))
                .collect(Collectors.toList());

        return new WalletDto(
                wallet.getId(),
                wallet.getName(),
                wallet.getBalance(),
                transactionDtos
        );
    }
}
