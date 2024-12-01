package org.example.smallbankexample.domain.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WalletDto {

    private Long id;
    private String name;
    private BigDecimal balance;
    private List<TransactionDto> transactions;
}
