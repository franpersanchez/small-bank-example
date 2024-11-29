package org.example.smallbankexample.infraestructure.addapters;

import org.example.smallbankexample.domain.models.Wallet;
import org.example.smallbankexample.domain.ports.port.WalletRepositoryPort;
import org.example.smallbankexample.infraestructure.addapters.repository.WalletRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class WalletSpringJpaAdapter implements WalletRepositoryPort {
    @Override
    public Wallet create(Wallet wallet) {
        return null;
    }

    @Override
    public Wallet findWalletById(Long walletId) {
        return null;
    }

    @Override
    public List<Wallet> findAllWallets() {
        return List.of();
    }

    @Override
    public Wallet update(Wallet wallet) {
        return null;
    }

    @Override
    public boolean deleteWalletById(Long walletId) {
        return false;
    }

    @Override
    public List<Wallet> findByUserId(Long userId) {
        return List.of();
    }
}
