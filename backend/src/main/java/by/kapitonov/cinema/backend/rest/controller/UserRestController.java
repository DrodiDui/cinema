package by.kapitonov.cinema.backend.rest.controller;

import by.kapitonov.cinema.backend.model.User;
import by.kapitonov.cinema.backend.rest.response.ApiResponse;
import by.kapitonov.cinema.backend.service.UserService;
import by.kapitonov.cinema.backend.service.dto.RegistrationUserDTO;
import by.kapitonov.cinema.backend.service.dto.user.CreateUserDTO;
import by.kapitonov.cinema.backend.service.dto.user.UserDTO;
import by.kapitonov.cinema.backend.service.mapper.UserMapper;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity getAll(Pageable pageable) {

        Page<UserDTO> userDTOS = userService.getAll(pageable);

        return new ResponseEntity(userDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getOne(@PathVariable(name = "id") Long id) {

        User user = userService.getById(id);
        UserDTO userDTO = UserMapper.toDTO(user);

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse> create(@RequestBody CreateUserDTO userDTO) {

        userService.create(userDTO);

        return new ResponseEntity<>(new ApiResponse("User successfully created"), HttpStatus.OK);
    }

    @PostMapping("/registration")
    public ResponseEntity<ApiResponse> registration(@RequestBody RegistrationUserDTO userDTO) {

        userService.register(userDTO);

        return new ResponseEntity<>(new ApiResponse("User successfully registration"), HttpStatus.OK);
    }

    @PutMapping("/{id}/role")
    public ResponseEntity<ApiResponse> updateRole(
            @PathVariable(name = "id") Long id,
            @RequestParam(name = "role") String roleName
    ) {

        userService.changeUserRole(id, roleName);

        return new ResponseEntity<>(new ApiResponse("User role successfully updated"), HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ApiResponse> updateStatus(
            @PathVariable(name = "id") Long id,
            @RequestParam(name = "status") String statusName
    ) {

        userService.changeUserStatus(id, statusName);

        return new ResponseEntity<>(new ApiResponse("User status successfully updated"), HttpStatus.OK);
    }
}
