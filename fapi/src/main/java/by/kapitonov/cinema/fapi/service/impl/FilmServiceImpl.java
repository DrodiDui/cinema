package by.kapitonov.cinema.fapi.service.impl;

import by.kapitonov.cinema.fapi.config.UrlConstants;
import by.kapitonov.cinema.fapi.model.Film;
import by.kapitonov.cinema.fapi.rest.request.SortRequest;
import by.kapitonov.cinema.fapi.rest.response.ApiResponse;
import by.kapitonov.cinema.fapi.rest.response.PageResponse;
import by.kapitonov.cinema.fapi.service.FilmService;
import by.kapitonov.cinema.fapi.service.dto.CreateFilmDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class FilmServiceImpl implements FilmService {

    private final RestTemplate restTemplate;

    public FilmServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public PageResponse<Film> getAll(int page, int size) {
        return restTemplate.getForObject(
                UrlConstants.FILM_URL + "?page=" + page + "&size=" + size,
                PageResponse.class
        );
    }

    @Override
    public List<Film> getAllFilmsByName(String filmName) {
        return restTemplate.getForObject(
                UrlConstants.FILM_URL + "/" + filmName + "/all",
                List.class);
    }

    @Override
    public Film getOne(Long filmId) {
        return restTemplate.getForObject(UrlConstants.FILM_URL + "/" + filmId, Film.class);
    }

    @Override
    public ApiResponse create(CreateFilmDTO filmDTO) {
        return restTemplate.postForObject(UrlConstants.FILM_URL, filmDTO, ApiResponse.class);
    }
}