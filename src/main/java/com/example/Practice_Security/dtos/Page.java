package com.example.Practice_Security.dtos;
import com.example.Practice_Security.dtos.userData.Data;
import com.example.Practice_Security.dtos.userData.Support;

@lombok.Data
public class Page {
    private Long page;
    private Data[] data;
    private Support support;
}
