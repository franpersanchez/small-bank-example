package org.example.smallbankexample.domain.ports.port;

import org.example.smallbankexample.domain.models.User;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepositoryPort {

    User create(User user);
    User findUserById(Long id);
    List<User> getAll();
    User getById(long id);
    User findByName(String username);
    User findByEmail(String email);


}
