package by.kapitonov.cinema.fapi.service;

import by.kapitonov.cinema.fapi.model.Film;
import by.kapitonov.cinema.fapi.rest.request.SortRequest;
import by.kapitonov.cinema.fapi.rest.response.ApiResponse;
import by.kapitonov.cinema.fapi.rest.response.PageResponse;
import by.kapitonov.cinema.fapi.service.dto.CreateFilmDTO;

import java.util.List;
import java.util.Map;

public interface FilmService {

    PageResponse<Film> getAll(Map<String, String> pageableParams);

    List<Film> getAllFilmsByName(String filmName);

    Film getOne(Long filmId);

    ApiResponse create(CreateFilmDTO createFilmDTO);

}
