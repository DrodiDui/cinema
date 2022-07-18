package com.kapitonau.cinema.adminservice.repository.custom;

import com.kapitonau.cinema.admin.dto.cinema.CinemaDto;
import com.kapitonau.cinema.adminservice.model.CinemaEntity;

import java.util.List;

public interface CinemaCustomRepository {

    List<CinemaEntity> findAllByFilters(Long offset, Long limit);

    Long cinemaCountByFilters();

}
