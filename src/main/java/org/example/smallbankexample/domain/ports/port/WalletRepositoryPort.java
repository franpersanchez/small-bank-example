package org.example.smallbankexample.domain.ports.port;

import org.example.smallbankexample.domain.models.Wallet;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WalletRepositoryPort {

    Wallet create(Wallet wallet);
    Optional<Wallet> findWalletById(UUID walletId);
    List<Wallet> findAllWallets();
    Optional<Wallet> update(Wallet wallet);
    boolean deleteWalletById(UUID walletId);
    List<Wallet> findByUserId(UUID userId);

}
