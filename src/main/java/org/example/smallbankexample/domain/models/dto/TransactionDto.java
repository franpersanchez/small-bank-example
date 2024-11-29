package org.example.smallbankexample.domain.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionDto {

    private Long id;
    private BigDecimal amount;
    private String description;
    private LocalDateTime transactionDate;


}