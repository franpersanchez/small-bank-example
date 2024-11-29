package org.example.smallbankexample.application.mapper;

import org.example.smallbankexample.domain.models.User;
import org.example.smallbankexample.domain.models.dto.UserDto;
import org.example.smallbankexample.domain.models.dto.request.UserRequest;
import org.springframework.stereotype.Component;


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
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getWallets()
        );
    }
}
