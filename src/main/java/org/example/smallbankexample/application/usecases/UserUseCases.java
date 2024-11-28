package org.example.smallbankexample.application.usecases;

import org.example.smallbankexample.domain.models.User;
import org.example.smallbankexample.domain.models.dto.UserDto;
import org.example.smallbankexample.domain.models.dto.request.UserRequest;

import java.util.Optional;

public interface UserUseCases {

    UserDto createUser(UserRequest userRequest);
}
