package com.mybike.service.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginUserServiceModel {
    private String username;
    private String password;

    public LoginUserServiceModel(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
