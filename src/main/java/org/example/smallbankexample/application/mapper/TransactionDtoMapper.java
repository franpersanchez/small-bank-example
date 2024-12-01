package org.example.smallbankexample.application.mapper;

import org.example.smallbankexample.domain.models.Transaction;
import org.example.smallbankexample.domain.models.dto.TransactionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {WalletDtoMapper.class})
public interface TransactionDtoMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "transactionDate", target = "transactionDate")
    TransactionDto toDto(Transaction domain);

}
