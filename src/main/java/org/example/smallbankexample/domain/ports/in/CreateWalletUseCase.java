package org.example.smallbankexample.domain.ports.in;

import org.example.smallbankexample.domain.models.User;
import org.example.smallbankexample.domain.models.Wallet;

import java.util.Optional;

public interface CreateWalletUseCase {
    Optional<Wallet> createWallet(User owner);
}
