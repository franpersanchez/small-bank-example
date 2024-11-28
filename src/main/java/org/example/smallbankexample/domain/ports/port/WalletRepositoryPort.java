package org.example.smallbankexample.domain.ports.port;

import org.example.smallbankexample.domain.models.Wallet;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WalletRepositoryPort {

    Wallet create(Wallet wallet);
    Optional<Wallet> findWalletById(Long walletId);
    List<Wallet> findAllWallets();
    Optional<Wallet> update(Wallet wallet);
    boolean deleteWalletById(Long walletId);
    List<Wallet> findByUserId(Long userId);

}
