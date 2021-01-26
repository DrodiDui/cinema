package by.kapitonov.cinema.fapi.service;

import by.kapitonov.cinema.fapi.model.Cinema;
import by.kapitonov.cinema.fapi.rest.response.ApiResponse;
import by.kapitonov.cinema.fapi.rest.response.PageResponse;
import by.kapitonov.cinema.fapi.service.dto.cinema.UpdateCinemaDTO;
import by.kapitonov.cinema.fapi.service.dto.cinema.CreateCinemaDTO;

import java.util.List;

public interface CinemaService {

    PageResponse<Cinema> getAll(int page, int size);
    PageResponse<Cinema> getAllOwnerCinemas(Long ownerId, int page, int size);

    List<Cinema> getAllOwnerCinemasByCountryAndCity(Long ownerId, String country, String city);

    Cinema getOne(String cinemaName);
    Cinema getOneByManagerId(Long managerId);

    ApiResponse create(CreateCinemaDTO cinemaDTO);

    ApiResponse update(UpdateCinemaDTO cinemaDTO);

    ApiResponse changeStatus(Long cinemaId, String statusName);

}
