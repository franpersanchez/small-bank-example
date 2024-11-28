package org.example.smallbankexample.infraestructure.addapters;

import org.example.smallbankexample.domain.models.User;
import org.example.smallbankexample.domain.ports.port.UserRepositoryPort;
import org.example.smallbankexample.infraestructure.addapters.entities.UserEntity;
import org.example.smallbankexample.infraestructure.addapters.mapper.UserDboMapper;
import org.example.smallbankexample.infraestructure.addapters.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;



@Service
@Transactional
public class UserSpringJpaAdapter implements UserRepositoryPort {

    private final UserRepository userRepository;
    private final UserDboMapper userDboMapper;

    @Autowired
    public UserSpringJpaAdapter(UserRepository userRepository, UserDboMapper userDboMapper) {
        this.userRepository = userRepository;
        this.userDboMapper = userDboMapper;
    }

    @Override
    public User create(User user) {
        UserEntity userEntity = UserDboMapper.toDbo(user);
        userRepository.save(userEntity);
        return userDboMapper.toDomain(userEntity);
    }

    @Override
    public User findUserById(Long id) {
        return null;
    }

    @Override
    public List<User> findAllUsers() {
        return List.of();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<User> update(User user) {
        return Optional.empty();
    }

    @Override
    public boolean deleteUserById(Long id) {
        return false;
    }
}
