package org.example.smallbankexample.infraestructure.addapters.mapper;

import org.example.smallbankexample.domain.models.Wallet;
import org.example.smallbankexample.infraestructure.addapters.entities.UserEntity;
import org.example.smallbankexample.infraestructure.addapters.entities.WalletEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class WalletDboMapper {

    public static WalletEntity toDbo(Wallet wallet) {
        if (wallet == null) {
            return null;
        }

        return new WalletEntity(
                wallet.getId(),
                wallet.getName(),
                wallet.getBalance(),
                null,
                null
        );
    }

    public Wallet toDomain(WalletEntity walletEntity) {
        if (walletEntity == null) {
            return null;
        }

        return new Wallet(
                walletEntity.getId(),
                walletEntity.getName(),
                walletEntity.getBalance(),
                walletEntity.getTransactions() != null ? walletEntity.getTransactions() : null
        );
    }
}
