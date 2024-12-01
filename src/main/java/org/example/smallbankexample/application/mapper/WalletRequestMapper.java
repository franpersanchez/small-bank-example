package org.example.smallbankexample.application.mapper;

import org.example.smallbankexample.domain.models.Wallet;
import org.example.smallbankexample.domain.models.dto.request.WalletRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WalletRequestMapper {

    @Mapping(source = "name", target = "name")
    Wallet toDomain(WalletRequest request);
}
