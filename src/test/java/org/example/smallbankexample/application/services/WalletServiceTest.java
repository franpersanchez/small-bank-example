package org.example.smallbankexample.application.services;

import org.example.smallbankexample.application.mapper.*;
import org.example.smallbankexample.domain.models.Transaction;
import org.example.smallbankexample.domain.models.Wallet;
import org.example.smallbankexample.domain.models.dto.TransactionDto;
import org.example.smallbankexample.domain.models.dto.WalletDto;
import org.example.smallbankexample.domain.models.dto.request.WalletRequest;
import org.example.smallbankexample.domain.ports.port.WalletRepositoryPort;
import org.example.smallbankexample.infraestructure.addapters.exceptions.UserException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class WalletServiceTest {

    @Mock
    WalletRepositoryPort walletRepositoryPort;

    @Mock
    WalletRequestMapper walletRequestMapper;

    @Mock
    WalletDtoMapper walletDtoMapper;

    @Mock
    TransactionDtoMapper transactionDtoMapper;

    @InjectMocks
    WalletManagementService walletManagementService;

    private WalletRequest walletRequest;
    private Wallet wallet;
    private WalletDto walletDto;
    private Transaction transaction;
    private TransactionDto transactionDto;
    private LocalDateTime timestamp;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        timestamp = LocalDateTime.now();
        walletRequest = new WalletRequest("MyWallet");
        wallet = new Wallet(1L, "MyWallet", BigDecimal.ZERO, new ArrayList<>());
        walletDto = new WalletDto(1L, "MyWallet", BigDecimal.ZERO, new ArrayList<>());
        transaction = new Transaction(1L, BigDecimal.valueOf(100), "Deposit", LocalDateTime.now());
        transactionDto = new TransactionDto(1L, BigDecimal.valueOf(100), "Deposit", LocalDateTime.now());

    }

    @Test
    void createWallet_success() {
        when(walletRequestMapper.toDomain(walletRequest)).thenReturn(wallet);
        when(walletRepositoryPort.save(wallet)).thenReturn(wallet);
        when(walletDtoMapper.toDto(wallet)).thenReturn(walletDto);

        WalletDto result = walletManagementService.createWallet(walletRequest);

        assertNotNull(result);
        assertEquals(walletDto.getName(), result.getName());
        verify(walletRepositoryPort).save(wallet);
        verify(walletRequestMapper).toDomain(walletRequest);
        verify(walletDtoMapper).toDto(wallet);
    }

    @Test
    void depositMoney_success() {
        when(walletRepositoryPort.findWalletById(1L)).thenReturn(wallet);
        when(transactionDtoMapper.toDto(any(Transaction.class))).thenReturn(transactionDto);

        TransactionDto result = walletManagementService.depositMoney(1L, BigDecimal.valueOf(100), timestamp);

        assertNotNull(result);
        assertEquals(transactionDto.getAmount(), result.getAmount());
        verify(walletRepositoryPort).save(wallet);
    }

    @Test
    void depositMoney_walletNotFound() {
        when(walletRepositoryPort.findWalletById(1L)).thenReturn(null);

        UserException exception = assertThrows(UserException.class, () ->
                walletManagementService.depositMoney(1L, BigDecimal.valueOf(100), timestamp)
        );

        assertEquals(HttpStatus.BAD_REQUEST, exception.getErrorCode());
        assertTrue(exception.getErrorMessage().contains("does not exist"));
    }

    @Test
    void getBalanceById_success() {
        when(walletRepositoryPort.findWalletById(1L)).thenReturn(wallet);
        when(walletDtoMapper.toDto(wallet)).thenReturn(walletDto);

        WalletDto result = walletManagementService.getBalanceById(1L);

        assertNotNull(result);
        assertEquals(walletDto.getBalance(), result.getBalance());
    }

    @Test
    void getBalanceById_walletNotFound() {
        when(walletRepositoryPort.findWalletById(1L)).thenReturn(null);

        UserException exception = assertThrows(UserException.class, () ->
                walletManagementService.getBalanceById(1L)
        );

        assertEquals(HttpStatus.BAD_REQUEST, exception.getErrorCode());
        assertTrue(exception.getErrorMessage().contains("does not exist"));
    }

    @Test
    void transferMoney_success() {
        Wallet walletFrom = new Wallet(2L, "MyWallet", BigDecimal.valueOf(100), new ArrayList<>());
        Wallet walletTo = new Wallet(3L, "OtherWallet", BigDecimal.valueOf(100), new ArrayList<>());

        when(walletRepositoryPort.findWalletById(2L)).thenReturn(walletFrom);
        when(walletRepositoryPort.findWalletById(3L)).thenReturn(walletTo);
        when(transactionDtoMapper.toDto(any(Transaction.class))).thenReturn(transactionDto);

        TransactionDto result = walletManagementService.transferMoney(2L, BigDecimal.valueOf(50), 3L, timestamp);

        assertNotNull(result);
        assertEquals(transactionDto.getAmount(), result.getAmount());
        verify(walletRepositoryPort, times(2)).save(any(Wallet.class));
    }

    @Test
    void transferMoney_insufficientFunds() {
        when(walletRepositoryPort.findWalletById(1L)).thenReturn(wallet);
        when(walletRepositoryPort.findWalletById(2L)).thenReturn(new Wallet(2L, "OtherWallet", BigDecimal.ZERO, Collections.emptyList()));

        UserException exception = assertThrows(UserException.class, () ->
                walletManagementService.transferMoney(1L, BigDecimal.valueOf(50), 2L, timestamp)
        );

        assertEquals(HttpStatus.BAD_REQUEST, exception.getErrorCode());
        assertTrue(exception.getErrorMessage().contains("Insufficient balance"));
    }

    @Test
    void getAllWallets_success() {
        when(walletRepositoryPort.findAllWallets()).thenReturn(List.of(wallet));
        when(walletDtoMapper.toDto(wallet)).thenReturn(walletDto);

        List<WalletDto> result = walletManagementService.getAllWallets();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(walletDto.getName(), result.get(0).getName());
    }
}