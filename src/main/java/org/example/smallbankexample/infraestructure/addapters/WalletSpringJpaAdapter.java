package org.example.smallbankexample.infraestructure.addapters;

import org.example.smallbankexample.application.mapper.WalletMapper;
import org.example.smallbankexample.domain.models.User;
import org.example.smallbankexample.domain.models.Wallet;
import org.example.smallbankexample.domain.ports.port.WalletRepositoryPort;
import org.example.smallbankexample.infraestructure.addapters.entities.UserEntity;
import org.example.smallbankexample.infraestructure.addapters.entities.WalletEntity;
import org.example.smallbankexample.infraestructure.addapters.mapper.UserDboMapper;
import org.example.smallbankexample.infraestructure.addapters.mapper.WalletDboMapper;
import org.example.smallbankexample.infraestructure.addapters.repository.WalletRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class WalletSpringJpaAdapter implements WalletRepositoryPort {

    private final WalletRepository walletRepository;
    private final WalletDboMapper walletDboMapper;
    private final UserDboMapper userDboMapper;
    private final WalletMapper walletMapper;

    public WalletSpringJpaAdapter(WalletRepository walletRepository, WalletDboMapper walletDboMapper, UserDboMapper userDboMapper, WalletMapper walletMapper) {
        this.walletRepository = walletRepository;
        this.walletDboMapper = walletDboMapper;
        this.userDboMapper = userDboMapper;
        this.walletMapper = walletMapper;
    }



    @Override
    public Wallet findWalletById(long walletId) {
        WalletEntity walletEntity = walletRepository.findById(walletId).orElse(null);
        if (walletEntity == null) {
            return null;
        }
        return walletDboMapper.toDomain(walletEntity);
    }

    @Override
    public Wallet save(Wallet wallet) {
        walletRepository.save(walletDboMapper.toDbo(wallet));
        return wallet;
    }

    @Override
    public List<Wallet> findAllWallets() {
        return walletRepository.findAll()
                .stream()
                .map(walletDboMapper::toDomain)
                .collect(Collectors.toUnmodifiableList());
    }

}
