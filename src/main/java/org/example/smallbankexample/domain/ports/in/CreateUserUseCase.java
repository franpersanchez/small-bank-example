package org.example.smallbankexample.domain.ports.in;

import org.example.smallbankexample.domain.models.User;

import java.util.Optional;

public interface CreateUserUseCase {
    Optional<User> createUser(String username, String password, String email);
}
