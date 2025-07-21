package com.example.Practice_Security.dtos.unknownData;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class UnknownData {
    @Id
    private Long id;
    private String name;
    private Integer year;
    private String color;
    @JsonProperty("pantone_value")
    private String pantone_value;
}
