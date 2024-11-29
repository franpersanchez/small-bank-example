package org.example.smallbankexample.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
    private List<Wallet> wallets;

    public User(String username, String email, String password) {
        this.name = username;
        this.email = email;
        this.password = password;
    }
}

