package org.example.smallbankexample.application.mapper;

import org.example.smallbankexample.domain.models.User;
import org.example.smallbankexample.domain.models.Wallet;
import org.example.smallbankexample.domain.models.dto.UserDto;
import org.example.smallbankexample.domain.models.dto.request.UserRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class UserMapper {

    public static User toDomain(UserRequest userRequest) {
        return new User(
                userRequest.getUsername(),
                userRequest.getEmail(),
                userRequest.getPassword()
        );
    }

    public static UserDto toDto(User user) {
        List<Wallet> wallets = user.getWallets();
        String walletsInfo = wallets.isEmpty()
                ? "No wallets yet"
                : "User has " + user.getWallets().size() + " wallet(s)";

        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getWallets(),
                walletsInfo

        );
    }
}
