package by.kapitonov.cinema.fapi.service.impl;

import by.kapitonov.cinema.fapi.config.UrlConstants;
import by.kapitonov.cinema.fapi.model.Hall;
import by.kapitonov.cinema.fapi.rest.response.ApiResponse;
import by.kapitonov.cinema.fapi.rest.response.PageResponse;
import by.kapitonov.cinema.fapi.service.HallService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HallServiceImpl implements HallService {

    private final RestTemplate restTemplate;

    public HallServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public PageResponse<Hall> getAll(int page, int size) {
        return restTemplate.getForObject(
                UrlConstants.HALL_URL + "?page=" + page + "&size=" + size,
                PageResponse.class
        );
    }

    @Override
    public Hall getOne(String hallName) {
        return restTemplate.getForObject(
                UrlConstants.HALL_URL + "/" + hallName,
                Hall.class
        );
    }

    @Override
    public ApiResponse create(Hall hall) {
        return restTemplate.postForEntity(
                UrlConstants.HALL_URL,
                hall,
                ApiResponse.class
        ).getBody();
    }

    @Override
    public ApiResponse update(Hall hall) {
        return restTemplate.patchForObject(
                UrlConstants.HALL_URL,
                hall,
                ApiResponse.class
        );
    }

    @Override
    public ApiResponse changeStatus(Long hallId, String statusName) {
        return restTemplate.patchForObject(
                UrlConstants.HALL_URL + "/" + hallId + "/status?status=" + statusName,
                null,
                ApiResponse.class
        );
    }
}
