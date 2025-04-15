package com.example.sematewebshop.domain;

import lombok.Getter;
import lombok.Setter;

public class LoginRequest {
    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private String password;

}
