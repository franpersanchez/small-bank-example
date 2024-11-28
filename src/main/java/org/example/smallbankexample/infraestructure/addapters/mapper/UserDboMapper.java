package org.example.smallbankexample.infraestructure.addapters.mapper;

import org.example.smallbankexample.domain.models.User;
import org.example.smallbankexample.infraestructure.addapters.entities.UserEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserDboMapper {

    public static UserEntity toDbo(User user) {
        if (user == null) {
            return null;
        }

        return new UserEntity(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),

                user.getWallets() != null ? user.getWallets().stream()
                        .map(wallet -> WalletDboMapper.toDbo(wallet, UserDboMapper.toDbo(user)))
                        .collect(Collectors.toList())
                        : null
        );
    }

    public static User toDomain(UserEntity userEntity) {
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
                        .map(WalletDboMapper::toDomain)
                        .collect(Collectors.toList())
                        : null
        );
    }
}