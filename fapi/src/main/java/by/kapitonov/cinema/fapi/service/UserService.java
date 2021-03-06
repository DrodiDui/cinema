package by.kapitonov.cinema.fapi.service;

import by.kapitonov.cinema.fapi.model.User;
import by.kapitonov.cinema.fapi.rest.response.ApiResponse;
import by.kapitonov.cinema.fapi.rest.response.PageResponse;
import by.kapitonov.cinema.fapi.service.dto.UpdateUserDTO;
import by.kapitonov.cinema.fapi.service.dto.user.CreateUserDTO;
import by.kapitonov.cinema.fapi.service.dto.user.RegistrationUserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.Map;

public interface UserService {

    PageResponse<User> getAll(Map<String, String> pageableParams);

    User getByEmail(String email);

    ApiResponse activateUser(String activationCode);

    ApiResponse create(CreateUserDTO userDTO);

    ApiResponse changeRole(Long userId, String roleName);
    ApiResponse changeStatus(Long userId, String statusName);

    ApiResponse updateUser(Long userId, UpdateUserDTO userDTO);
}
