package by.kapitonov.cinema.fapi.service.impl;

import by.kapitonov.cinema.fapi.config.UrlConstants;
import by.kapitonov.cinema.fapi.model.User;
import by.kapitonov.cinema.fapi.rest.response.ApiResponse;
import by.kapitonov.cinema.fapi.rest.response.PageResponse;
import by.kapitonov.cinema.fapi.service.UserService;
import by.kapitonov.cinema.fapi.service.dto.user.CreateUserDTO;
import by.kapitonov.cinema.fapi.service.dto.user.RegistrationUserDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements UserService {

    private final RestTemplate restTemplate;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(
            RestTemplateBuilder restTemplateBuilder,
            PasswordEncoder passwordEncoder
    ) {
        this.restTemplate = restTemplateBuilder.build();
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public PageResponse<User> getAll(Integer page, Integer size) {

        PageResponse<User> pageResponse =
                restTemplate.getForObject(
                        UrlConstants.USER_URL + "?page=" + page + "&size=" + size,
                        PageResponse.class
                );
        return pageResponse;
    }

    @Override
    public User getByEmail(String email) {
        return restTemplate.getForObject(UrlConstants.USER_URL + "/" + email, User.class);
    }

    @Override
    public ApiResponse create(CreateUserDTO userDTO) {

        String hashPassword = passwordEncoder.encode(userDTO.getPassword());
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(hashPassword);
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setRoleName(userDTO.getRoleName());
        user.setStatusName(userDTO.getStatusName());

        return restTemplate.postForEntity(UrlConstants.USER_URL, user, ApiResponse.class).getBody();
    }

    @Override
    public ApiResponse changeRole(Long userId, String roleName) {
        return restTemplate.patchForObject(
                UrlConstants.USER_URL + "/" + userId + "/role?role=" + roleName,
                null,
                ApiResponse.class);
    }

    @Override
    public ApiResponse changeStatus(Long userId, String statusName) {
        return restTemplate.patchForObject(
                UrlConstants.USER_URL + "/" + userId + "/status?status=" + statusName,
                null,
                ApiResponse.class);
    }


}
