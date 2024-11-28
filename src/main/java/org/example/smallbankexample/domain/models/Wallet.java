package org.example.smallbankexample.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.smallbankexample.domain.models.valueObject.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public final class Wallet {
    private Long id;
    private String name;
    private BigDecimal balance;
    private List<Transaction> transactions;

}
