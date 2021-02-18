package by.kapitonov.cinema.backend.service;

import by.kapitonov.cinema.backend.service.dto.UpdateUserDTO;
import by.kapitonov.cinema.backend.model.User;
import by.kapitonov.cinema.backend.service.dto.user.RegistrationUserDTO;
import by.kapitonov.cinema.backend.service.dto.user.CreateUserDTO;
import by.kapitonov.cinema.backend.service.dto.user.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<UserDTO> getAll(Pageable pageable);

    User getById(Long id);
    User getByEmail(String email);
    User activateUserAccount(String activationCode);

    User create(CreateUserDTO userDTO);
    User register(RegistrationUserDTO userDTO);

    User update(Long userId, UpdateUserDTO updateUserDTO);

    User changeUserRole(Long id, String roleName);
    User changeUserStatus(Long id, String statusName);

}
