package org.example.smallbankexample.application.mapper;

import org.example.smallbankexample.domain.models.Wallet;
import org.example.smallbankexample.domain.models.dto.WalletDto;
import org.example.smallbankexample.domain.models.dto.request.WalletRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class WalletMapper {

    public static Wallet toDomain(WalletRequest walletRequest) {
        return new Wallet(
                walletRequest.getName(),
                BigDecimal.ZERO
        );
    }

    public static WalletDto toDto(Wallet wallet) {
        return new WalletDto(
                wallet.getName(),
                wallet.getBalance()
        );
    }
}
