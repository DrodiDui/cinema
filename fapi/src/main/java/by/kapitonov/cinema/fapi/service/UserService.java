package by.kapitonov.cinema.fapi.service;

import by.kapitonov.cinema.fapi.model.User;
import by.kapitonov.cinema.fapi.rest.response.ApiResponse;
import by.kapitonov.cinema.fapi.rest.response.PageResponse;
import by.kapitonov.cinema.fapi.service.dto.user.CreateUserDTO;
import by.kapitonov.cinema.fapi.service.dto.user.RegistrationUserDTO;

public interface UserService {
    PageResponse<User> getAll(Integer page, Integer size);

    User getByEmail(String email);

    ApiResponse create(CreateUserDTO userDTO);
    ApiResponse registration(RegistrationUserDTO userDTO);

    ApiResponse changeRole(Long userId, String roleName);
    ApiResponse changeStatus(Long userId, String statusName);
}
