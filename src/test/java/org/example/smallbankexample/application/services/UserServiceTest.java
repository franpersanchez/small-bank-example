package org.example.smallbankexample.application.services;

import org.example.smallbankexample.application.mapper.UserMapper;
import org.example.smallbankexample.domain.models.User;
import org.example.smallbankexample.domain.models.dto.UserDto;
import org.example.smallbankexample.domain.models.dto.request.UserRequest;
import org.example.smallbankexample.domain.ports.port.UserRepositoryPort;

import org.example.smallbankexample.infraestructure.addapters.exceptions.UserException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


public class UserServiceTest {

    @Mock
     UserRepositoryPort userRepositoryPort;

    @Mock
    UserMapper userMapper;

    @InjectMocks
    UserService userService;

    private UserRequest userRequest;
    private User userDomain;
    private UserDto userDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        userRequest = new UserRequest("franpersanchez", "franpersanchez@gmail.com", "password1234");
        userDomain = new User(1L, "franpersanchez", "franpersanchez@gmail.com", "password1234", null);
        userDto = new UserDto(1L, "franpersanchez", "franpersanchez@gmail.com");

    }
    @Test
    void createUser_success() {
        Mockito.when(userRepositoryPort.findByName(any(String.class))).thenReturn(null);
        Mockito.when(userRepositoryPort.findByEmail(any(String.class))).thenReturn(null);
        Mockito.when(userRepositoryPort.create(any(User.class))).thenReturn(userDomain);

        UserDto createdUser = userService.createUser(userRequest);

        assertNotNull(createdUser);
        assertEquals(userDto.getName(), createdUser.getName());
        assertEquals(userDto.getEmail(), createdUser.getEmail());
    }

    @Test
    void createUser_emailAlreadyExists() {
        when(userRepositoryPort.findByName("franpersanchez")).thenReturn(null);
        when(userRepositoryPort.findByEmail("franpersanchez@gmail.com")).thenReturn(userDomain);

        UserException exception = assertThrows(UserException.class, () -> {
            userService.createUser(userRequest);
        });

        assertEquals(HttpStatus.BAD_REQUEST, exception.getErrorCode());
        assertEquals("User with email franpersanchez@gmail.com is already registered.", exception.getErrorMessage());
    }

    @Test
    void createUser_usernameAlreadyExists() {
        when(userRepositoryPort.findByName("franpersanchez")).thenReturn(userDomain);
        when(userRepositoryPort.findByEmail("franpersanchez@gmail.com")).thenReturn(null);

        UserException exception = assertThrows(UserException.class, () -> {
            userService.createUser(userRequest);
        });

        assertEquals(HttpStatus.BAD_REQUEST, exception.getErrorCode());
        assertEquals("User with username franpersanchez is already registered.", exception.getErrorMessage());
    }



}