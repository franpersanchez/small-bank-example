package org.example.smallbankexample.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public final class User {
    private Long id;
    private String name;
    private String email;
    private String password;
    private List<Wallet> wallets = new ArrayList<>();

    public User(String username, String email, String password) {
        this.name = username;
        this.email = email;
        this.password = password;
    }

    public void addWallet(Wallet wallet){
        this.wallets.add(wallet);
    }
}

