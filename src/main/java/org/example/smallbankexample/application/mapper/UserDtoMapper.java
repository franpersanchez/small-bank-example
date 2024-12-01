package org.example.smallbankexample.application.mapper;

import org.example.smallbankexample.domain.models.User;
import org.example.smallbankexample.domain.models.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = {WalletDtoMapper.class})
public interface UserDtoMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    UserDto toDto(User domain);

}
