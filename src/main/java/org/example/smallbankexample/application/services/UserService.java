package org.example.smallbankexample.application.services;

import org.example.smallbankexample.application.usecases.UserUseCases;
import org.example.smallbankexample.domain.models.User;
import org.example.smallbankexample.domain.ports.port.UserRepositoryPort;
import org.example.smallbankexample.infraestructure.addapters.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UserService implements UserUseCases {

    private final UserRepositoryPort userRepositoryPort;


    @Autowired
    public UserService(final UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public Optional<User> createUser(UserRequest request) {
        UserRequest userRequest = userRequestMapper.toDomain(request);
        userRepositoryPort.create(userRequest);
    }
}
