package by.kapitonov.cinema.fapi.service;

import by.kapitonov.cinema.fapi.model.Cinema;
import by.kapitonov.cinema.fapi.rest.response.ApiResponse;
import by.kapitonov.cinema.fapi.rest.response.PageResponse;
import by.kapitonov.cinema.fapi.service.dto.cinema.UpdateCinemaDTO;
import by.kapitonov.cinema.fapi.service.dto.cinema.CreateCinemaDTO;

public interface CinemaService {

    PageResponse<Cinema> getAll(int page, int size);
    PageResponse<Cinema> getAllOwnerCinemas(Long ownerId, int page, int size);

    Cinema getOne(String cinemaName);

    ApiResponse create(CreateCinemaDTO cinemaDTO);

    ApiResponse update(UpdateCinemaDTO cinemaDTO);

    ApiResponse changeStatus(Long cinemaId, String statusName);

}
