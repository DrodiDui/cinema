package by.kapitonov.cinema.fapi.service;

import by.kapitonov.cinema.fapi.model.Hall;
import by.kapitonov.cinema.fapi.rest.response.ApiResponse;
import by.kapitonov.cinema.fapi.rest.response.PageResponse;
import by.kapitonov.cinema.fapi.service.dto.CreateHallDTO;
import by.kapitonov.cinema.fapi.service.dto.UpdateHallDTO;

public interface HallService {

    PageResponse<Hall> getAll(Long cinemaId, int page, int size);

    Hall getOne(String cinemaName, String hallName);

    ApiResponse create(CreateHallDTO hallDTO);

    ApiResponse update(UpdateHallDTO hallDTO);

    ApiResponse changeStatus(Long hallId, String statusName);

}
