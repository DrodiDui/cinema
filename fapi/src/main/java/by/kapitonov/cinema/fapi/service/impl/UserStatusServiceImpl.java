package by.kapitonov.cinema.fapi.service.impl;

import by.kapitonov.cinema.fapi.config.UrlConstants;
import by.kapitonov.cinema.fapi.rest.response.ApiResponse;
import by.kapitonov.cinema.fapi.service.UserStatusService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserStatusServiceImpl implements UserStatusService {

    private final RestTemplate restTemplate;

    public UserStatusServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.rootUri(UrlConstants.USER_STATUS_URL).build();
    }

    @Override
    public List<String> getAll(){
        return restTemplate.getForObject("", List.class);
    }

    @Override
    public ApiResponse create(String statusName) {
        return restTemplate.postForEntity("?status=" + statusName, null, ApiResponse.class).getBody();
    }
}
