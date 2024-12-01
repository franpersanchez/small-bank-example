package org.example.smallbankexample.domain.ports.port;


import org.example.smallbankexample.domain.models.Wallet;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface WalletRepositoryPort {


    Wallet findWalletById(long walletId);
    Wallet save(Wallet wallet);

    List<Wallet> findAllWallets();

}
