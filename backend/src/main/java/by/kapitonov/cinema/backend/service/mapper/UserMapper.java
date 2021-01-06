package by.kapitonov.cinema.backend.service.mapper;

import by.kapitonov.cinema.backend.exception.ModelNotFoundException;
import by.kapitonov.cinema.backend.model.User;
import by.kapitonov.cinema.backend.service.dto.user.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserDTO toDTO(User user) {
        if (user == null) {
            throw new ModelNotFoundException("User hasn't been found");
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setRoleName(user.getRole().getRoleName());
        userDTO.setStatusName(user.getStatus().getStatusName());
        return userDTO;
    }

    public static List<UserDTO> userDTOs(List<User> users) {
        return users.stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

}
