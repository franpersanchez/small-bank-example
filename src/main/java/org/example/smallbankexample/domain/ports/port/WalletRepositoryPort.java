package org.example.smallbankexample.domain.ports.port;

import org.example.smallbankexample.domain.models.User;
import org.example.smallbankexample.domain.models.Wallet;
import org.example.smallbankexample.infraestructure.addapters.entities.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface WalletRepositoryPort {


    Wallet findWalletById(long walletId);
    Wallet save(Wallet wallet);

    List<Wallet> findAllWallets();

}
