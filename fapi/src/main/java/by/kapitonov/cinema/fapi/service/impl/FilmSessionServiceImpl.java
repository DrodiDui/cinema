package by.kapitonov.cinema.fapi.service.impl;

import by.kapitonov.cinema.fapi.config.UrlConstants;
import by.kapitonov.cinema.fapi.model.FilmSession;
import by.kapitonov.cinema.fapi.rest.response.ApiResponse;
import by.kapitonov.cinema.fapi.rest.response.PageResponse;
import by.kapitonov.cinema.fapi.service.FilmSessionService;
import by.kapitonov.cinema.fapi.service.dto.CreatFilmSessionDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FilmSessionServiceImpl implements FilmSessionService {

    private final RestTemplate restTemplate;

    public FilmSessionServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.rootUri(UrlConstants.FILM_SESSION_URL).build();
    }

    @Override
    public PageResponse<FilmSession> getAll(int page, int size) {
        return restTemplate.getForObject("?page=" + page + "&size=" + size, PageResponse.class);
    }

    @Override
    public FilmSession getOne(Long sessionId) {
        return restTemplate.getForObject("/" + sessionId, FilmSession.class);
    }

    @Override
    public ApiResponse create(CreatFilmSessionDTO filmSessionDTO) {
        return restTemplate.postForEntity("", filmSessionDTO, ApiResponse.class).getBody();
    }
}
