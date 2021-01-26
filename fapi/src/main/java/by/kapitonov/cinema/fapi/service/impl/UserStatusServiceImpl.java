package by.kapitonov.cinema.fapi.service.impl;

import by.kapitonov.cinema.fapi.config.UrlConstants;
import by.kapitonov.cinema.fapi.rest.response.ApiResponse;
import by.kapitonov.cinema.fapi.service.UserStatusService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserStatusServiceImpl implements UserStatusService {

    private final RestTemplate restTemplate;

    public UserStatusServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<String> getAll(){
        return restTemplate.getForObject(UrlConstants.USER_STATUS_URL, List.class);
    }

    @Override
    public ApiResponse create(String statusName) {
        return restTemplate.postForEntity(UrlConstants.USER_STATUS_URL + "?status=" + statusName, null, ApiResponse.class).getBody();
    }
}
