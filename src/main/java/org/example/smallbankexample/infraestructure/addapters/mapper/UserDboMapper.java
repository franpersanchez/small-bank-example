package org.example.smallbankexample.infraestructure.addapters.mapper;

import org.example.smallbankexample.domain.models.User;
import org.example.smallbankexample.infraestructure.addapters.entities.UserEntity;
import org.example.smallbankexample.infraestructure.addapters.entities.WalletEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDboMapper {

    private final WalletDboMapper walletDboMapper;

    public UserDboMapper(WalletDboMapper walletDboMapper) {
        this.walletDboMapper = walletDboMapper;
    }

    public static UserEntity toDbo(User user) {
        if (user == null) {
            return null;
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setName(user.getName());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());

        if (user.getWallets() != null) {
            List<WalletEntity> walletEntities = user.getWallets().stream()
                    .map(wallet -> new WalletEntity(wallet.getId(), wallet.getName(), wallet.getBalance(), null, userEntity))
                    .collect(Collectors.toList());
            userEntity.setWallets(walletEntities);
        }

        return userEntity;

    }

    public User toDomain(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }

        return new User(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getWallets() != null
                        ? userEntity.getWallets().stream()
                        .map(walletDboMapper::toDomain)
                        .collect(Collectors.toList())
                        : null
        );
    }
}
