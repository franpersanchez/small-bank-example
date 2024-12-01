package org.example.smallbankexample.application.mapper;

import org.example.smallbankexample.domain.models.User;
import org.example.smallbankexample.domain.models.dto.request.UserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface UserRequestMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    User toDomain(UserRequest request);
}
