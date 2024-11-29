package org.example.smallbankexample.infraestructure.rest.controller;

import org.example.smallbankexample.application.services.WalletManagementService;
import org.example.smallbankexample.application.usecases.WalletService;
import org.example.smallbankexample.domain.models.dto.WalletDto;
import org.example.smallbankexample.domain.models.dto.request.WalletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}")
    public WalletDto getBalanceById(@PathVariable long id){
        return walletService.getBalanceById(id);
    }

    @GetMapping
    public List<WalletDto> getAll() {
        return walletService.getAllWallets();
    }



}