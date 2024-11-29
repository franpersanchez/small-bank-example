package org.example.smallbankexample.application.services;

import org.example.smallbankexample.application.mapper.UserMapper;
import org.example.smallbankexample.application.usecases.UserUseCases;

import org.example.smallbankexample.domain.models.User;
import org.example.smallbankexample.domain.models.constants.UserConstant;
import org.example.smallbankexample.domain.models.dto.UserDto;
import org.example.smallbankexample.domain.models.dto.request.UserRequest;
import org.example.smallbankexample.domain.ports.port.UserRepositoryPort;

import org.example.smallbankexample.infraestructure.addapters.exceptions.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserUseCases {

    private final UserRepositoryPort userRepositoryPort;
    private final UserMapper userMapper;


    @Autowired
    public UserService(UserRepositoryPort userRepositoryPort, UserMapper userMapper) {
        this.userRepositoryPort = userRepositoryPort;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto createUser(UserRequest request) {
        User userRequest = userMapper.toDomain(request);
        User existingUserByUsername = userRepositoryPort.findByName(request.getUsername());
        User existingUserByEmail = userRepositoryPort.findByEmail(request.getEmail());

        if (existingUserByUsername != null) {
            throw new UserException(HttpStatus.BAD_REQUEST,
                    String.format(UserConstant.USER_HAS_USERNAME_ALREADY_REGISTERED, userRequest.getName()));
        }

        if (existingUserByEmail != null) {
            throw new UserException(HttpStatus.BAD_REQUEST,
                    String.format(UserConstant.USER_HAS_EMAIL_ALREADY_REGISTERED, userRequest.getEmail()));
        }
        User userCreated = userRepositoryPort.create(userRequest);
        if (userCreated == null) {
            throw new UserException(HttpStatus.INTERNAL_SERVER_ERROR,
                    String.format(UserConstant.ERROR_OCCURRED_ON_USER_CREATION, userRequest.getName()));
        }
        return userMapper.toDto(userCreated);
    }
}
