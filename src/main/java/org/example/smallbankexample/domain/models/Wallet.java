package org.example.smallbankexample.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public final class Wallet {
    private Long id;
    private String name;
    private BigDecimal balance;
    private Map<String, LocalDateTime> transactions;

    public Wallet(String name, BigDecimal zero) {
        this.name = name;
        this.balance = zero;
    }
}
