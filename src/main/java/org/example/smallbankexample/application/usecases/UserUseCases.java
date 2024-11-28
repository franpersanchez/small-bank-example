package org.example.smallbankexample.application.usecases;

import org.example.smallbankexample.domain.models.User;

import java.util.Optional;

public interface UserUseCases {

    Optional<User> createUser(String username, String password, String email);
}
