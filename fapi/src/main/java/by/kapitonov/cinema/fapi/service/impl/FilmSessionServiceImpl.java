package by.kapitonov.cinema.fapi.service.impl;

import by.kapitonov.cinema.fapi.config.UrlConstants;
import by.kapitonov.cinema.fapi.model.FilmSession;
import by.kapitonov.cinema.fapi.rest.response.ApiResponse;
import by.kapitonov.cinema.fapi.rest.response.PageResponse;
import by.kapitonov.cinema.fapi.service.FilmSessionService;
import by.kapitonov.cinema.fapi.service.dto.CreateFilmSessionDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FilmSessionServiceImpl implements FilmSessionService {

    private final RestTemplate restTemplate;


    public FilmSessionServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public PageResponse<FilmSession> getAll(int page, int size) {
        return restTemplate.getForObject(UrlConstants.FILM_SESSION_URL + "?page=" + page + "&size=" + size, PageResponse.class);
    }

    @Override
    public PageResponse<FilmSession> getAllActiveSessionByHallId(Long hallId, int page, int size) {
        return restTemplate.getForObject(
                UrlConstants.FILM_SESSION_URL + "/" + hallId + "/active?page=" + page + "&size=" + size,
                PageResponse.class
        );
    }

    @Override
    public FilmSession getOne(Long sessionId) {
        return restTemplate.getForObject(UrlConstants.FILM_SESSION_URL + "/" + sessionId, FilmSession.class);
    }

    @Override
    public ApiResponse create(CreateFilmSessionDTO filmSessionDTO) {
        return restTemplate.postForEntity(UrlConstants.FILM_SESSION_URL, filmSessionDTO, ApiResponse.class).getBody();
    }
}
