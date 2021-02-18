package by.kapitonov.cinema.backend.service.impl;

import by.kapitonov.cinema.backend.exception.ModelAlreadyExistsException;
import by.kapitonov.cinema.backend.exception.ModelNotFoundException;
import by.kapitonov.cinema.backend.repository.UserRepository;
import by.kapitonov.cinema.backend.service.CinemaService;
import by.kapitonov.cinema.backend.service.RoleService;
import by.kapitonov.cinema.backend.service.UserService;
import by.kapitonov.cinema.backend.service.UserStatusService;
import by.kapitonov.cinema.backend.service.dto.UpdateUserDTO;
import by.kapitonov.cinema.backend.service.mapper.UserMapper;
import by.kapitonov.cinema.backend.config.Constants;
import by.kapitonov.cinema.backend.model.Cinema;
import by.kapitonov.cinema.backend.model.Role;
import by.kapitonov.cinema.backend.model.User;
import by.kapitonov.cinema.backend.model.UserStatus;
import by.kapitonov.cinema.backend.service.dto.user.RegistrationUserDTO;
import by.kapitonov.cinema.backend.service.dto.user.CreateUserDTO;
import by.kapitonov.cinema.backend.service.dto.user.UserDTO;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final UserStatusService userStatusService;
    private final CinemaService cinemaService;

    public UserServiceImpl(
            UserRepository userRepository,
            RoleService roleService,
            UserStatusService userStatusService,
            @Lazy CinemaService cinemaService
    ) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.userStatusService = userStatusService;
        this.cinemaService = cinemaService;
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
    public User activateUserAccount(String activationCode) {
        return userRepository.findByActivationCode(activationCode)
                .map(user -> {
                    user.setActivationCode(null);
                    user.setStatus(getUserStatus(Constants.ACTIVE_STATUS));
                    return userRepository.save(user);
                })
                .orElseThrow(
                        () -> new ModelNotFoundException("User hasn't been found by activation code: " + activationCode)
                );
    }

    @Override
    public User create(CreateUserDTO userDTO) {

        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new ModelAlreadyExistsException("User already exists");
        }

        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setActivationCode(null);
        user.setRole(roleService.getByName(userDTO.getRoleName()));
        user.setStatus(getUserStatus(userDTO.getStatusName()));

        if (userDTO.getRoleName().equals(Constants.MANAGER_ROLE)) {
            user.setCinema(getCinema(userDTO.getCinemaId()));
        }

        return userRepository.save(user);
    }

    @Override
    public User register(RegistrationUserDTO userDTO) {

        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new ModelAlreadyExistsException("User already exists");
        }

        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setActivationCode(userDTO.getActivationCode());
        user.setRole(roleService.getByName(Constants.USER_ROLE));
        user.setStatus(getUserStatus(Constants.INACTIVE_STATUS));

        return userRepository.save(user);
    }

    @Override
    public User update(Long userId, UpdateUserDTO updateUserDTO) {
        return userRepository.findById(userId)
                .map(user -> {
                    user.setFirstName(updateUserDTO.getFirstName());
                    user.setLastName(updateUserDTO.getLastName());
                    return userRepository.save(user);
                })
                .orElseThrow(
                        () -> new ModelNotFoundException("User hasn't been found by id: " + userId)
                );
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

    private Cinema getCinema(Long cinemaId) {
        return cinemaService.getById(cinemaId);
    }

    private UserStatus getUserStatus(String status) {
        return userStatusService.getByName(status);
    }
}
