package by.kapitonov.cinema.fapi.service;

import by.kapitonov.cinema.fapi.model.User;
import by.kapitonov.cinema.fapi.service.dto.CreateUserDTO;
import by.kapitonov.cinema.fapi.service.dto.RegistrationUserDTO;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User getByEmail(String email);

    User create(CreateUserDTO userDTO);
    User registration(RegistrationUserDTO userDTO);

}
