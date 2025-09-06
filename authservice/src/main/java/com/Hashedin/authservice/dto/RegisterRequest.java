package com.Hashedin.authservice.dto;

import lombok.Data;

@Data
public class RegisterRequest {

    private String name;
    private String userName;
    private String email;
    private String password;
}
