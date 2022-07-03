package com.kapitonau.cinema.commonspring.dto;

public record CommonExceptionResponse(

        String code,
        String devMessage,
        String userMessage

) {
}
