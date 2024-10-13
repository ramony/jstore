package com.raybyte.jstore.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UserDO {
    private String username;
    private String password;
    private Set<String> roles;

    public UserDO(String username, String password, Set<String> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}
