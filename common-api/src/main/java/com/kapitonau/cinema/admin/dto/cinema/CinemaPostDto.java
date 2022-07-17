package com.kapitonau.cinema.admin.dto.cinema;

public record CinemaPostDto(

        String cinemaName,
        String country,
        String city,
        String address,
        String description,
        String cinemaStatus

) {
}
