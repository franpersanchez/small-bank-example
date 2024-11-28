package org.example.smallbankexample.application.services;

import org.example.smallbankexample.application.mapper.UserMapper;
import org.example.smallbankexample.application.usecases.UserUseCases;

import org.example.smallbankexample.domain.models.User;
import org.example.smallbankexample.domain.models.dto.UserDto;
import org.example.smallbankexample.domain.models.dto.request.UserRequest;
import org.example.smallbankexample.domain.ports.port.UserRepositoryPort;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UserService implements UserUseCases {

    private final UserRepositoryPort userRepositoryPort;
    private final UserMapper userMapper;


    @Autowired
    public UserService(final UserRepositoryPort userRepositoryPort, UserMapper userMapper) {
        this.userRepositoryPort = userRepositoryPort;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto createUser(UserRequest request) {
        User userRequest = userMapper.toDomain(request);
        User userCreated = userRepositoryPort.create(userRequest);
        if (userCreated == null) {
            new RuntimeException("Error when registering new user");
        }
        return userMapper.toDto(userCreated);
    }
}
