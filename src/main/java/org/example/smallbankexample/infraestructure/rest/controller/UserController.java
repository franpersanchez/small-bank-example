package org.example.smallbankexample.infraestructure.rest.controller;

import org.example.smallbankexample.application.services.UserManagementService;
import org.example.smallbankexample.application.usecases.UserService;
import org.example.smallbankexample.domain.models.dto.UserDto;
import org.example.smallbankexample.domain.models.dto.request.UserRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public UserDto create(@RequestBody UserRequest userRequest){
        return userService.createUser(userRequest);
    }

    @PostMapping("/{id}/{wallet}")
    public UserDto assignWallet(@PathVariable Long id , @PathVariable Long wallet){
        return userService.assignWallet(id, wallet);
    }

    @GetMapping
    public List<UserDto> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable long id){
        return userService.getById(id);
    }
}
