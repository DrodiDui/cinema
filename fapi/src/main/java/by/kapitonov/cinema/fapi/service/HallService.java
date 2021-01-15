package by.kapitonov.cinema.fapi.service;

import by.kapitonov.cinema.fapi.model.Hall;
import by.kapitonov.cinema.fapi.rest.response.ApiResponse;
import by.kapitonov.cinema.fapi.rest.response.PageResponse;

public interface HallService {

    PageResponse<Hall> getAll(Long cinemaId, int page, int size);

    Hall getOne(String hallName);

    ApiResponse create(Hall hall);

    ApiResponse update(Hall hall);

    ApiResponse changeStatus(Long hallId, String statusName);

}
