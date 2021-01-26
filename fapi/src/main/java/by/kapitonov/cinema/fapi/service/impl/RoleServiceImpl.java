package by.kapitonov.cinema.fapi.service.impl;

import by.kapitonov.cinema.fapi.config.UrlConstants;
import by.kapitonov.cinema.fapi.rest.response.ApiResponse;
import by.kapitonov.cinema.fapi.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RestTemplate restTemplate;

    public RoleServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<String> getAll() {
        return restTemplate.getForObject(UrlConstants.ROLE_URL, List.class);
    }

    @Override
    public ApiResponse create(String roleName) {
        return restTemplate.postForEntity(UrlConstants.ROLE_URL + "?role=" + roleName, null, ApiResponse.class).getBody();
    }
}
