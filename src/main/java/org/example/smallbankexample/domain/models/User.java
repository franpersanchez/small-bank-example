package org.example.smallbankexample.domain.models;

import lombok.Getter;

import java.util.UUID;

@Getter
public final class User {
    private UUID id;
    private String name;
    private String email;
    private String password;
}

