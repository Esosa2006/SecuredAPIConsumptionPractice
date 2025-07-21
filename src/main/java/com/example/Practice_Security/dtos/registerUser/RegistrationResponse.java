package com.example.Practice_Security.dtos.registerUser;

import lombok.Data;

@Data
public class RegistrationResponse {
    private Long id;
    private String token;
}
