package org.example.smallbankexample.domain.ports.port;

import org.example.smallbankexample.domain.models.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryPort {
    User create(User user);
    User findUserById(Long id);
    List<User> findAllUsers();
    User findByName(String username);
    User findByEmail(String email);
    User update(User user);
    boolean deleteUserById(Long id);

}
