package com.example.Practice_Security.dtos.userCreate;

import lombok.Data;

import java.util.Date;

@Data
public class UserCreateResponse {
    private String name;
    private String job;
    private Long id;
    private Date createdAt;
}
