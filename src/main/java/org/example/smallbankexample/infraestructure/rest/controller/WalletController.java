package org.example.smallbankexample.infraestructure.rest.controller;

import org.example.smallbankexample.application.services.UserService;
import org.example.smallbankexample.application.services.WalletService;
import org.example.smallbankexample.domain.models.dto.UserDto;
import org.example.smallbankexample.domain.models.dto.WalletDto;
import org.example.smallbankexample.domain.models.dto.request.UserRequest;
import org.example.smallbankexample.domain.models.dto.request.WalletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wallets")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping()
    public WalletDto create(@RequestBody WalletRequest walletRequest) {
        return walletService.createWallet(walletRequest);
    }
}