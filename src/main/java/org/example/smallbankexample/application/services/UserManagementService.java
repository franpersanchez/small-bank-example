package org.example.smallbankexample.application.services;

import org.example.smallbankexample.application.mapper.UserMapper;
import org.example.smallbankexample.application.usecases.UserService;

import org.example.smallbankexample.domain.models.User;
import org.example.smallbankexample.domain.models.Wallet;
import org.example.smallbankexample.domain.models.constants.UserConstant;
import org.example.smallbankexample.domain.models.constants.WalletConstant;
import org.example.smallbankexample.domain.models.dto.UserDto;
import org.example.smallbankexample.domain.models.dto.request.UserRequest;
import org.example.smallbankexample.domain.ports.port.UserRepositoryPort;

import org.example.smallbankexample.domain.ports.port.WalletRepositoryPort;
import org.example.smallbankexample.infraestructure.addapters.entities.UserEntity;
import org.example.smallbankexample.infraestructure.addapters.exceptions.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserManagementService implements UserService {

    private final UserRepositoryPort userRepositoryPort;
    private final UserMapper userMapper;
    private final WalletRepositoryPort walletRepositoryPort;


    @Autowired
    public UserManagementService(UserRepositoryPort userRepositoryPort, UserMapper userMapper, WalletRepositoryPort walletRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
        this.userMapper = userMapper;
        this.walletRepositoryPort = walletRepositoryPort;
    }

    @Override
    public UserDto createUser(UserRequest request) {
        User userRequest = userMapper.toDomain(request);
        User existingUserByUsername = userRepositoryPort.findByName(request.getUsername());
        User existingUserByEmail = userRepositoryPort.findByEmail(request.getEmail());

        if (existingUserByUsername != null) {
            throw new UserException(HttpStatus.BAD_REQUEST,
                    String.format(UserConstant.USER_HAS_USERNAME_ALREADY_REGISTERED, userRequest.getName()));
        }

        if (existingUserByEmail != null) {
            throw new UserException(HttpStatus.BAD_REQUEST,
                    String.format(UserConstant.USER_HAS_EMAIL_ALREADY_REGISTERED, userRequest.getEmail()));
        }
        User userCreated = userRepositoryPort.create(userRequest);
        if (userCreated == null) {
            throw new UserException(HttpStatus.INTERNAL_SERVER_ERROR,
                    String.format(UserConstant.ERROR_OCCURRED_ON_USER_CREATION, userRequest.getName()));
        }
        return userMapper.toDto(userCreated);
    }

    @Override
    public List<UserDto> getAll() {
        List<User> users = userRepositoryPort.getAll();
        return users
                .stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public UserDto getById(long id) {
        User userFound = userRepositoryPort.getById(id);
        if(userFound == null) {
            throw new UserException(HttpStatus.NOT_FOUND, String.format(UserConstant.USER_NOT_FOUND, id));
        }
        return UserMapper.toDto(userFound);
    }

    @Override
    public UserDto assignWallet(Long id, Long walletId) {
        User userFound = userRepositoryPort.getById(id);
        if (userFound == null) {
            throw new UserException(HttpStatus.NOT_FOUND, String.format(UserConstant.USER_NOT_FOUND, id));
        }
        Wallet walletFound = walletRepositoryPort.findWalletById(walletId);
        if (walletFound == null) {
            throw new UserException(HttpStatus.NOT_FOUND, String.format(WalletConstant.WALLET_DOES_NOT_EXIST, walletId));
        }
        userFound.getWallets().add(walletFound);
        return userMapper.toDto(userRepositoryPort.update(userFound));
    }
}
