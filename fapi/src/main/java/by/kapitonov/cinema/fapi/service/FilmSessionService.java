package by.kapitonov.cinema.fapi.service;

import by.kapitonov.cinema.fapi.model.FilmSession;
import by.kapitonov.cinema.fapi.rest.response.ApiResponse;
import by.kapitonov.cinema.fapi.rest.response.PageResponse;
import by.kapitonov.cinema.fapi.service.dto.CreateFilmSessionDTO;

public interface FilmSessionService {

    PageResponse<FilmSession> getAll(int page, int size);
    PageResponse<FilmSession> getAllActiveSessionByHallId(Long hallId, int page, int size);

    FilmSession getOne(Long sessionId);

    ApiResponse create(CreateFilmSessionDTO filmSessionDTO);

}
