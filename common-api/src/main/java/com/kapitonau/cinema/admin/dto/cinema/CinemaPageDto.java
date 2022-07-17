package com.kapitonau.cinema.admin.dto.cinema;

import java.time.ZonedDateTime;
import java.util.List;

public record CinemaPageDto(

        Long recordsCount,
        List<CinemaDto> cinemas,
        ZonedDateTime lastRequestTime

) {
}
