package com.example.Practice_Security.dtos;

import com.example.Practice_Security.dtos.unknownData.UnknownData;
import com.example.Practice_Security.dtos.userData.Support;
import lombok.Data;

@Data
public class UnknownPage {
    private Long page;
    private UnknownData[] data;
    private Support support;
}
