package org.example.smallbankexample.application.services;

import org.example.smallbankexample.application.mapper.WalletMapper;
import org.example.smallbankexample.domain.models.User;
import org.example.smallbankexample.domain.models.Wallet;
import org.example.smallbankexample.domain.models.dto.WalletDto;
import org.example.smallbankexample.domain.models.dto.request.WalletRequest;
import org.example.smallbankexample.domain.ports.port.UserRepositoryPort;
import org.example.smallbankexample.domain.ports.port.WalletRepositoryPort;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class WalletServiceTest {
    @Mock
    WalletRepositoryPort walletRepositoryPort;

    @Mock
    UserRepositoryPort userRepositoryPort;

    @Mock
    WalletMapper walletMapper;

    @InjectMocks
    WalletManagementService walletManagementService;

    private WalletRequest walletRequest;
    private Wallet walletDomain;
    private WalletDto walletDto;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        walletRequest = new WalletRequest("cartera NFTs", 1L);
        walletDomain = new Wallet(1L, "cartera NFTs", BigDecimal.ZERO, null );
        walletDto = new WalletDto(1L, "cartera NFTs", BigDecimal.ZERO);
        user = new User(1L, "franpersanchez", "franpersanchez@gmail.com", "password1234", null);

    }

    @AfterEach
    void tearDown() {
        reset(walletRepositoryPort, userRepositoryPort, walletMapper);
    }

//    @Ignore
//    @Test
//    void creatWallet_existingUser_success() {
//
//        when(userRepositoryPort.findUserById(walletRequest.getUserId())).thenReturn(user);
//
//        when(walletRepositoryPort.create(any(Wallet.class), eq(user))).thenReturn(walletDomain);
//
//        WalletDto result = walletService.createWallet(walletRequest);
//
//        assertNotNull(result);
//        assertEquals(walletDto.getId(), result.getId());
//        assertEquals(walletDto.getName(), result.getName());
//        assertEquals(walletDto.getBalance(), result.getBalance());
//
//        verify(userRepositoryPort, times(1)).findUserById(walletRequest.getUserId());
//
//    }

//    @Test
//    void creatWallet_nonExistingUser() {
//        when(userRepositoryPort.findUserById(walletRequest.getUserId())).thenReturn(null);
//        WalletException exception = assertThrows(WalletException.class, () -> walletService.createWallet(walletRequest));
//        assertEquals(WalletConstant.USER_DOES_NOT_EXIST, exception.getErrorMessage());
//
//    }

}
