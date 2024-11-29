package org.example.smallbankexample.domain.models.dto.request;

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
public class WalletRequest {

    private Long id;
    private String name;
    private BigDecimal balance;
    private List<String> transactions;
}
