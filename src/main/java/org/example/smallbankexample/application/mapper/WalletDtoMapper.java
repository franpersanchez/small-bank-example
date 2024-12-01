package org.example.smallbankexample.application.mapper;

import org.example.smallbankexample.domain.models.Wallet;
import org.example.smallbankexample.domain.models.dto.WalletDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface WalletDtoMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "balance", target = "balance")
    WalletDto toDto(Wallet wallet);
}
