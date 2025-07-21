package com.example.Practice_Security.exceptions;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public record Exception(String message, HttpStatus httpStatus, ZonedDateTime zonedDateTime) {
}
