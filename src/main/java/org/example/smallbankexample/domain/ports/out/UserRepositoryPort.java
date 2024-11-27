package org.example.smallbankexample.domain.ports.out;

import org.example.smallbankexample.domain.models.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryPort {
    User save(User user);
    User findUserById(UUID id);
    List<User> findAllUsers();
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> update(User user);
    boolean deleteUserById(UUID id);

}
