package by.kapitonov.cinema.backend.service;

import by.kapitonov.cinema.backend.model.User;
import by.kapitonov.cinema.backend.service.dto.user.CreateUserDTO;
import by.kapitonov.cinema.backend.service.dto.user.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<UserDTO> getAll(Pageable pageable);

    User getById(Long id);
    User getByEmail(String email);

    User create(CreateUserDTO userDTO);

    User changeUserRole(Long id, String roleName);
    User changeUserStatus(Long id, String statusName);

}
