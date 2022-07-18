package com.kapitonau.cinema.adminservice.controller;

import com.kapitonau.cinema.admin.api.CinemaApi;
import com.kapitonau.cinema.admin.dto.cinema.CinemaDto;
import com.kapitonau.cinema.admin.dto.cinema.CinemaPageDto;
import com.kapitonau.cinema.admin.dto.cinema.CinemaPostDto;
import com.kapitonau.cinema.adminservice.service.CinemaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CinemaRestController implements CinemaApi {

    private final CinemaService cinemaService;

    @Override
    public CinemaDto getCinemaById(Long cinemaId) {
        return cinemaService.getById(cinemaId);
    }

    @Override
    public CinemaPageDto getAllCinemas(Long offset, Long limit) {
        return cinemaService.getAllCinema(offset, limit);
    }

    @Override
    public CinemaDto createCinema(CinemaPostDto body) {
        return cinemaService.create(body);
    }
}
