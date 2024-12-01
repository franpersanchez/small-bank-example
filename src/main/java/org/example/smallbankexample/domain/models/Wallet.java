package org.example.smallbankexample.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Wallet {

    private Long id;
    private String name;
    private BigDecimal balance = BigDecimal.ZERO;
    private List<Transaction> transactions;
}
