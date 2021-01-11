package by.kapitonov.cinema.fapi.service;

import by.kapitonov.cinema.fapi.model.FilmSession;
import by.kapitonov.cinema.fapi.rest.response.ApiResponse;
import by.kapitonov.cinema.fapi.rest.response.PageResponse;
import by.kapitonov.cinema.fapi.service.dto.CreatFilmSessionDTO;

public interface FilmSessionService {

    PageResponse<FilmSession> getAll(int page, int size);

    FilmSession getOne(Long sessionId);

    ApiResponse create(CreatFilmSessionDTO filmSessionDTO);

}
