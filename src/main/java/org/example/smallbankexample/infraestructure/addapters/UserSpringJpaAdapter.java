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
import java.util.stream.Collectors;


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
        Optional<UserEntity> userEntity = userRepository.findById(id);
        return userEntity.map(userDboMapper::toDomain).orElse(null);
    }

    @Override
    public List<User> findAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream()
                .map(userDboMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public User findByName(String username) {
        Optional<UserEntity> userEntity = userRepository.findByName(username);
        return userEntity.map(userDboMapper::toDomain).orElse(null);
    }

    @Override
    public User findByEmail(String email) {
        Optional<UserEntity> userEntity = userRepository.findByEmail(email);
        return userEntity.map(userDboMapper::toDomain).orElse(null);
    }

    @Override
    public User update(User user) {
        Optional<UserEntity> userFound = userRepository.findById(user.getId());
        if (userFound.isPresent()) {
            UserEntity userEntity = userFound.get();
            userEntity.setName(user.getName());
            userEntity.setEmail(user.getEmail());
            userEntity.setPassword(user.getPassword());

            userRepository.save(userEntity);

            return userDboMapper.toDomain(userEntity);
        }
        return null;
    }

    @Override
    public boolean deleteUserById(Long id) {
        Optional<UserEntity> userFound = userRepository.findById(id);
        if (userFound.isPresent()) {
            userRepository.delete(userFound.get());
            return true;
        }
        return false;
    }
}
