package org.example.smallbankexample.domain.ports.port;

import org.example.smallbankexample.domain.models.User;
import org.example.smallbankexample.domain.models.Wallet;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface WalletRepositoryPort {

    Wallet create(Wallet wallet, User user);
    Wallet findWalletById(Long walletId);
    List<Wallet> findAllWallets();
    Wallet update(Wallet wallet);
    boolean deleteWalletById(Long walletId);
    List<Wallet> findByUserId(Long userId);

}
