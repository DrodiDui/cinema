package com.kapitonau.cinema.adminservice.service;

import com.kapitonau.cinema.admin.dto.cinema.CinemaDto;
import com.kapitonau.cinema.admin.dto.cinema.CinemaPageDto;
import com.kapitonau.cinema.admin.dto.cinema.CinemaPostDto;
import com.kapitonau.cinema.adminservice.model.CinemaEntity;
import com.kapitonau.cinema.commonspring.dto.EmptyDto;

public interface CinemaService {

    CinemaPageDto getAllCinema(Long offset, Long limit);

    CinemaDto getById(Long cinemaId);

    CinemaDto create(CinemaPostDto body);

    CinemaDto change(CinemaEntity cinemaEntity);

    EmptyDto delete(Long cinemaId);


}
