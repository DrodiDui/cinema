package by.kapitonov.cinema.backend.service.impl;

import by.kapitonov.cinema.backend.config.Constants;
import by.kapitonov.cinema.backend.exception.ModelNotFoundException;
import by.kapitonov.cinema.backend.model.Role;
import by.kapitonov.cinema.backend.model.User;
import by.kapitonov.cinema.backend.model.UserStatus;
import by.kapitonov.cinema.backend.repository.UserRepository;
import by.kapitonov.cinema.backend.service.RoleService;
import by.kapitonov.cinema.backend.service.UserService;
import by.kapitonov.cinema.backend.service.UserStatusService;
import by.kapitonov.cinema.backend.service.dto.RegistrationUserDTO;
import by.kapitonov.cinema.backend.service.dto.user.CreateUserDTO;
import by.kapitonov.cinema.backend.service.dto.user.UserDTO;
import by.kapitonov.cinema.backend.service.mapper.UserMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final UserStatusService userStatusService;

    public UserServiceImpl(
            UserRepository userRepository,
            RoleService roleService,
            UserStatusService userStatusService
    ) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.userStatusService = userStatusService;
    }

    @Override
    public Page<UserDTO> getAll(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(UserMapper::toDTO);
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(
                        () -> new ModelNotFoundException("User hasn't been found by id: " + id)
                );
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(
                        () -> new ModelNotFoundException("User hasn't been found by email: " + email)
                );
    }

    @Override
    public User create(CreateUserDTO userDTO) {

        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setRole(roleService.getByName(userDTO.getRoleName()));
        user.setStatus(userStatusService.getByName(userDTO.getStatusName()));

        return userRepository.save(user);
    }

    @Override
    public User register(RegistrationUserDTO userDTO) {

        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setRole(roleService.getByName(Constants.USER_ROLE));
        user.setStatus(userStatusService.getByName(Constants.INACTIVE_STATUS));

        return userRepository.save(user);
    }

    @Override
    public User changeUserRole(Long id, String roleName) {
        Role newRole = roleService.getByName(roleName);
        return userRepository.findById(id)
                .map(user -> {
                    user.setRole(newRole);
                    return userRepository.save(user);
                })
                .orElseThrow(
                        () -> new ModelNotFoundException("User hasn't been found")
                );
    }

    @Override
    public User changeUserStatus(Long id, String statusName) {
        UserStatus newStatus = userStatusService.getByName(statusName);
        return userRepository.findById(id)
                .map(user -> {
                    user.setStatus(newStatus);
                    return userRepository.save(user);
                })
                .orElseThrow(
                        () -> new ModelNotFoundException("User hasn't been found")
                );
    }
}
