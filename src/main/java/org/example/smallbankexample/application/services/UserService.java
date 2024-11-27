package org.example.smallbankexample.application.services;

import org.example.smallbankexample.domain.models.User;
import org.example.smallbankexample.domain.ports.in.CreateUserUseCase;

import java.util.Optional;

public class UserService implements CreateUserUseCase {
    @Override
    public Optional<User> createUser(String username, String password, String email) {
        return Optional.empty();
    }
}
