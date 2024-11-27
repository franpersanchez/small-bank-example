package org.example.smallbankexample.domain.ports.in;

import org.example.smallbankexample.domain.models.Wallet;

import java.math.BigDecimal;

public interface GetBalanceUseCase {
    BigDecimal getBalance(Wallet wallet);
}
