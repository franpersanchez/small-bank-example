package org.example.smallbankexample.application.usecases;

import org.example.smallbankexample.domain.models.User;
import org.example.smallbankexample.domain.models.dto.UserDto;

import java.util.Optional;

public interface UserUseCases {

    UserDto createUser(UserRequest request);
}
