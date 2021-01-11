package by.kapitonov.cinema.fapi.service.impl;

import by.kapitonov.cinema.fapi.config.UrlConstants;
import by.kapitonov.cinema.fapi.rest.response.ApiResponse;
import by.kapitonov.cinema.fapi.service.RoleService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RestTemplate restTemplate;

    public RoleServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.rootUri(UrlConstants.ROLE_URL).build();
    }

    @Override
    public List<String> getAll() {
        return restTemplate.getForObject("", List.class);
    }

    @Override
    public ApiResponse create(String roleName) {
        return restTemplate.postForEntity("?role=" + roleName, null, ApiResponse.class).getBody();
    }
}
