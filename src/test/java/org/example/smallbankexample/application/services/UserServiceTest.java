package org.example.smallbankexample.application.services;

import org.example.smallbankexample.application.mapper.UserDtoMapper;
import org.example.smallbankexample.application.mapper.UserRequestMapper;
import org.example.smallbankexample.domain.models.User;
import org.example.smallbankexample.domain.models.Wallet;
import org.example.smallbankexample.domain.models.constants.UserConstant;
import org.example.smallbankexample.domain.models.constants.WalletConstant;
import org.example.smallbankexample.domain.models.dto.UserDto;
import org.example.smallbankexample.domain.models.dto.request.UserRequest;
import org.example.smallbankexample.domain.ports.port.UserRepositoryPort;
import org.example.smallbankexample.domain.ports.port.WalletRepositoryPort;
import org.example.smallbankexample.infraestructure.addapters.exceptions.UserException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class UserServiceTest {

    @Mock
     UserRepositoryPort userRepositoryPort;

    @Mock
    WalletRepositoryPort walletRepositoryPort;

    @Mock
    UserRequestMapper userRequestMapper;

    @Mock
    UserDtoMapper userDtoMapper;

    @InjectMocks
    UserManagementService userManagementService;

    private UserRequest userRequest;
    private User userDomain;
    private UserDto userDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        userRequest = new UserRequest("Fran", "franpersanchez@gmail.com", "1234" );
        userDto = new UserDto(1L, "Fran", "franpersanchez@gmail.com");
        userDomain = new User(1L, "Fran", "franpersanchez@gmail.com", "1234", new ArrayList<>());

    }

    @Test
    void createUser_success() {
        when(userRequestMapper.toDomain(userRequest)).thenReturn(userDomain);
        when(userRepositoryPort.findByName(userRequest.getName())).thenReturn(null);
        when(userRepositoryPort.findByEmail(userRequest.getEmail())).thenReturn(null);
        when(userRepositoryPort.create(userDomain)).thenReturn(userDomain);
        when(userDtoMapper.toDto(userDomain)).thenReturn(userDto);

        UserDto result = userManagementService.createUser(userRequest);

        assertNotNull(result);
        assertEquals("Fran", result.getName());
        assertEquals("franpersanchez@gmail.com", result.getEmail());

        verify(userRepositoryPort).create(userDomain);
        verify(userDtoMapper).toDto(userDomain);
    }

    @Test
    void createUser_emailAlreadyExists() {
        when(userRequestMapper.toDomain(userRequest)).thenReturn(userDomain);
        when(userRepositoryPort.findByName(userRequest.getName())).thenReturn(null);
        when(userRepositoryPort.findByEmail(userRequest.getEmail())).thenReturn(userDomain);


        UserException exception = assertThrows(UserException.class, () -> {
            userManagementService.createUser(userRequest);
        });

        assertEquals(HttpStatus.BAD_REQUEST, exception.getErrorCode());
        String expectedErrorMessage = String.format(UserConstant.USER_HAS_EMAIL_ALREADY_REGISTERED, userRequest.getEmail());
        assertEquals(expectedErrorMessage, exception.getErrorMessage());
    }

    @Test
    void createUser_usernameAlreadyExists() {
        when(userRequestMapper.toDomain(userRequest)).thenReturn(userDomain);
        when(userRepositoryPort.findByName(userRequest.getName())).thenReturn(userDomain);
        when(userRepositoryPort.findByEmail(userRequest.getEmail())).thenReturn(null);

        UserException exception = assertThrows(UserException.class, () -> {
            userManagementService.createUser(userRequest);
        });

        assertEquals(HttpStatus.BAD_REQUEST, exception.getErrorCode());
        String expectedErrorMessage = String.format(UserConstant.USER_HAS_USERNAME_ALREADY_REGISTERED, userRequest.getName());
        assertEquals(expectedErrorMessage, exception.getErrorMessage());
    }

    @Test
    void getAll_success() {
        List<User> userList = List.of(userDomain);
        when(userRepositoryPort.getAll()).thenReturn(userList);
        when(userDtoMapper.toDto(userDomain)).thenReturn(userDto);

        List<UserDto> result = userManagementService.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Fran", result.get(0).getName());
        verify(userRepositoryPort).getAll();
    }

    @Test
    void getById_success() {
        when(userRepositoryPort.getById(1L)).thenReturn(userDomain);
        when(userDtoMapper.toDto(userDomain)).thenReturn(userDto);

        UserDto result = userManagementService.getById(1L);

        assertNotNull(result);
        assertEquals("Fran", result.getName());
        assertEquals("franpersanchez@gmail.com", result.getEmail());
        verify(userRepositoryPort).getById(1L);
    }

    @Test
    void getById_userNotFound() {
        when(userRepositoryPort.getById(1L)).thenReturn(null);

        UserException exception = assertThrows(UserException.class, () -> {
            userManagementService.getById(1L);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getErrorCode());
        String expectedErrorMessage = String.format(UserConstant.USER_NOT_FOUND, 1L);
        assertEquals(expectedErrorMessage, exception.getErrorMessage());
    }

    @Test
    void assignWallet_success() {
        Wallet wallet = new Wallet(1L, "NFTs", null, null);
        when(userRepositoryPort.getById(1L)).thenReturn(userDomain);
        when(walletRepositoryPort.findWalletById(1L)).thenReturn(wallet);
        when(userRepositoryPort.update(userDomain)).thenReturn(userDomain);
        when(userDtoMapper.toDto(userDomain)).thenReturn(userDto);

        UserDto result = userManagementService.assignWallet(1L, 1L);

        assertNotNull(result);
        assertEquals("Fran", result.getName());
        verify(userRepositoryPort).update(userDomain);
    }

    @Test
    void assignWallet_userNotFound() {
        when(userRepositoryPort.getById(1L)).thenReturn(null);

        UserException exception = assertThrows(UserException.class, () -> {
            userManagementService.assignWallet(1L, 1L);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getErrorCode());
        String expectedErrorMessage = String.format(WalletConstant.USER_DOES_NOT_EXIST, 1L);
        assertEquals(expectedErrorMessage, exception.getErrorMessage());
    }

    @Test
    void assignWallet_walletNotFound() {
        when(userRepositoryPort.getById(1L)).thenReturn(userDomain);
        when(walletRepositoryPort.findWalletById(1L)).thenReturn(null);

        UserException exception = assertThrows(UserException.class, () -> {
            userManagementService.assignWallet(1L, 1L);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getErrorCode());
        String expectedErrorMessage = String.format(WalletConstant.WALLET_DOES_NOT_EXIST, 1L);
        assertEquals(expectedErrorMessage, exception.getErrorMessage());
    }


}