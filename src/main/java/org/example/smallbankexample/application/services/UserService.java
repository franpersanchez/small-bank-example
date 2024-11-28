package org.example.smallbankexample.application.services;

import org.example.smallbankexample.application.usecases.UserUseCases;
import org.example.smallbankexample.domain.models.User;


import java.util.Optional;

public class UserService implements UserUseCases {
    @Override
    public Optional<User> createUser(String username, String password, String email) {
        return Optional.empty();
    }
}
