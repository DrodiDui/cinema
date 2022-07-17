package com.kapitonau.cinema.admin.api;

import com.kapitonau.cinema.admin.dto.cinema.CinemaDto;
import com.kapitonau.cinema.admin.dto.cinema.CinemaPageDto;
import com.kapitonau.cinema.admin.dto.cinema.CinemaPostDto;
import org.springframework.web.bind.annotation.*;

public interface CinemaApi {

    @GetMapping("/cinemas/{cinemaId}")
    CinemaDto getCinemaById(@PathVariable(value = "cinemaId") Long cinemaId);

    @GetMapping("/cinemas")
    CinemaPageDto getAllCinemas(@RequestParam(name = "offset", defaultValue = "0") Long offset, @RequestParam(name = "limit", defaultValue = "50") Long limit);

    @PostMapping("/cinemas")
    CinemaDto createCinema(@RequestBody CinemaPostDto body);

}
