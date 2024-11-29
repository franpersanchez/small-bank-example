package org.example.smallbankexample.infraestructure.addapters;

import org.example.smallbankexample.domain.models.User;
import org.example.smallbankexample.domain.ports.port.UserRepositoryPort;
import org.example.smallbankexample.infraestructure.addapters.entities.UserEntity;
import org.example.smallbankexample.infraestructure.addapters.exceptions.UserException;
import org.example.smallbankexample.infraestructure.addapters.mapper.UserDboMapper;
import org.example.smallbankexample.infraestructure.addapters.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        UserEntity userEntity = userDboMapper.toDbo(user);
        userRepository.save(userEntity);
        return userDboMapper.toDomain(userEntity);
    }

    @Override
    public User findUserById(Long id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        return userEntity.map(userDboMapper::toDomain).orElse(null);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll()
                .stream()
                .map(userDboMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public User getById(long id) {
        UserEntity user = userRepository.findById(id).orElse(null);
        return userDboMapper.toDomain(user);
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

}
