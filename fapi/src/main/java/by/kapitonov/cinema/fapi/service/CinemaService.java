package by.kapitonov.cinema.fapi.service;

import by.kapitonov.cinema.fapi.model.Cinema;
import by.kapitonov.cinema.fapi.rest.response.ApiResponse;
import by.kapitonov.cinema.fapi.rest.response.PageResponse;
import by.kapitonov.cinema.fapi.service.dto.cinema.UpdateCinemaDTO;
import by.kapitonov.cinema.fapi.service.dto.cinema.CreateCinemaDTO;

import java.util.List;
import java.util.Map;

public interface CinemaService {

    PageResponse<Cinema> getAll(Map<String, String> pageableParams);
    PageResponse<Cinema> getAllOwnerCinemas(Long ownerId, Map<String, String> pageableParams);

    Cinema getOne(String cinemaName);
    Cinema getOneByManagerId(Long managerId);

    ApiResponse create(CreateCinemaDTO cinemaDTO);

    ApiResponse update(UpdateCinemaDTO cinemaDTO);

    ApiResponse changeStatus(Long cinemaId, String statusName);
}
