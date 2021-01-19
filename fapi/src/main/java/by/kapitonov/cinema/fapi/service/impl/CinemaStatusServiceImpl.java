package by.kapitonov.cinema.fapi.service.impl;

import by.kapitonov.cinema.fapi.config.UrlConstants;
import by.kapitonov.cinema.fapi.rest.response.ApiResponse;
import by.kapitonov.cinema.fapi.service.CinemaStatusService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CinemaStatusServiceImpl implements CinemaStatusService {

    private final RestTemplate restTemplate;

    public CinemaStatusServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<String> getAllStatusNames() {
        return restTemplate.getForObject(
                UrlConstants.CINEMA_STATUS_URL,
                List.class
        );
    }

    @Override
    public ApiResponse create(String statusName) {
        return restTemplate.postForEntity(
                UrlConstants.CINEMA_STATUS_URL + "?status=" + statusName,
                null,
                ApiResponse.class
        ).getBody();
    }
}
