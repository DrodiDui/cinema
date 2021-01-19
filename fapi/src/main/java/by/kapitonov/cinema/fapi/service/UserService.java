package by.kapitonov.cinema.fapi.service;

import by.kapitonov.cinema.fapi.model.User;
import by.kapitonov.cinema.fapi.rest.response.ApiResponse;
import by.kapitonov.cinema.fapi.rest.response.PageResponse;
import by.kapitonov.cinema.fapi.service.dto.user.CreateUserDTO;
import by.kapitonov.cinema.fapi.service.dto.user.RegistrationUserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

public interface UserService {

    PageResponse<User> getAll(Integer page, Integer size);

    User getByEmail(String email);

    ApiResponse create(CreateUserDTO userDTO);

    ApiResponse changeRole(Long userId, String roleName);
    ApiResponse changeStatus(Long userId, String statusName);
}
