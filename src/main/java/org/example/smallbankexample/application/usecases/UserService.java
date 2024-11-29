package org.example.smallbankexample.application.usecases;

import org.example.smallbankexample.domain.models.dto.UserDto;
import org.example.smallbankexample.domain.models.dto.request.UserRequest;

import java.util.List;

public interface UserService {

    UserDto createUser(UserRequest userRequest);
    List<UserDto> getAll();
    UserDto getById(long id);
    UserDto assignWallet(Long id, Long walletId);
}
