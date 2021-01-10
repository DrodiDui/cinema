package by.kapitonov.cinema.fapi.service;

import by.kapitonov.cinema.fapi.model.User;
import by.kapitonov.cinema.fapi.rest.response.PageResponse;
import by.kapitonov.cinema.fapi.service.dto.CreateUserDTO;
import by.kapitonov.cinema.fapi.service.dto.RegistrationUserDTO;

public interface UserService {
    PageResponse<User> getAll(Integer page, Integer size);

    User getByEmail(String email);

    User create(CreateUserDTO userDTO);
    User registration(RegistrationUserDTO userDTO);

}
