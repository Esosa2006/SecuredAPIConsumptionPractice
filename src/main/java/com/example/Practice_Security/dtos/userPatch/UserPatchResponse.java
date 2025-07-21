package com.example.Practice_Security.dtos.userPatch;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class UserPatchResponse {
    private String name;
    private String job;
    private Date updatedAt;
}
