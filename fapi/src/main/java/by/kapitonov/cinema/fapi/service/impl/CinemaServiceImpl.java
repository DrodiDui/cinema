package by.kapitonov.cinema.fapi.service.impl;

import by.kapitonov.cinema.fapi.config.UrlConstants;
import by.kapitonov.cinema.fapi.model.Cinema;
import by.kapitonov.cinema.fapi.rest.response.ApiResponse;
import by.kapitonov.cinema.fapi.rest.response.PageResponse;
import by.kapitonov.cinema.fapi.service.CinemaService;
import by.kapitonov.cinema.fapi.service.dto.cinema.CreateCinemaDTO;
import by.kapitonov.cinema.fapi.service.dto.cinema.UpdateCinemaDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CinemaServiceImpl implements CinemaService {

    private final RestTemplate restTemplate;

    public CinemaServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public PageResponse<Cinema> getAll(int page, int size) {
        return restTemplate.getForObject(
                UrlConstants.CINEMA_URL + "?page=" + page + "&size=" + size,
                PageResponse.class
        );
    }

    @Override
    public PageResponse<Cinema> getAllOwnerCinemas(Long ownerId, int page, int size) {
        return restTemplate.getForObject(
                UrlConstants.CINEMA_URL + "/" + ownerId + "/all?page=" + page + "&size=" + size,
                PageResponse.class
        );
    }

    @Override
    public Cinema getOne(String cinemaName) {
        return restTemplate.getForObject(UrlConstants.CINEMA_URL + "/" + cinemaName, Cinema.class);
    }

    @Override
    public ApiResponse create(CreateCinemaDTO cinemaDTO) {
        return restTemplate.postForEntity(UrlConstants.CINEMA_URL, cinemaDTO, ApiResponse.class).getBody();
    }

    @Override
    public ApiResponse update(UpdateCinemaDTO cinemaDTO) {
        return restTemplate.patchForObject(UrlConstants.CINEMA_URL, cinemaDTO, ApiResponse.class);
    }

    @Override
    public ApiResponse changeStatus(Long cinemaId, String statusName) {
        return restTemplate.patchForObject(
                UrlConstants.CINEMA_URL + "/" + cinemaId + "/status?status=" + statusName,
                null,
                ApiResponse.class
        );
    }
}
