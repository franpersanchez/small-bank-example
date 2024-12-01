package org.example.smallbankexample.application.services;

import org.example.smallbankexample.application.mapper.UserDtoMapper;
import org.example.smallbankexample.application.mapper.UserRequestMapper;
import org.example.smallbankexample.domain.models.User;
import org.example.smallbankexample.domain.models.constants.UserConstant;
import org.example.smallbankexample.domain.models.dto.UserDto;
import org.example.smallbankexample.domain.models.dto.request.UserRequest;
import org.example.smallbankexample.domain.ports.port.UserRepositoryPort;
import org.example.smallbankexample.infraestructure.addapters.exceptions.UserException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class UserServiceTest {

    @Mock
     UserRepositoryPort userRepositoryPort;

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
        userDomain = new User(1L, "Fran", "franpersanchez@gmail.com", "1234", null);

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



}