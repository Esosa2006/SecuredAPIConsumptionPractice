package com.example.Practice_Security.dtos.userData;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
public class Data {
    private Long id;
    private String email;
    @JsonProperty("first_name")
    private String first_name;
    @JsonProperty("last_name")
    private String last_name;
    private String avatar;
}
