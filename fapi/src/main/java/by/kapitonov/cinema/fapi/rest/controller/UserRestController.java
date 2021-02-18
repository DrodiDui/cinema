package by.kapitonov.cinema.fapi.rest.controller;

import by.kapitonov.cinema.fapi.model.User;
import by.kapitonov.cinema.fapi.rest.response.ApiResponse;
import by.kapitonov.cinema.fapi.rest.response.PageResponse;
import by.kapitonov.cinema.fapi.service.UserService;
import by.kapitonov.cinema.fapi.service.dto.UpdateUserDTO;
import by.kapitonov.cinema.fapi.service.dto.user.CreateUserDTO;
import org.springframework.data.repository.query.Param;
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

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity getAll(
            @RequestParam Map<String, String> pageableParams
    ) {

        PageResponse<User> users = userService.getAll(pageableParams);

        return new ResponseEntity(users, HttpStatus.OK);
    }

    @GetMapping("/{email}")
    public ResponseEntity<User> getOne(@PathVariable(name = "email") String email) {

        User user = userService.getByEmail(email);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/activate/{code}")
    public ResponseEntity<ApiResponse> activateUser(@PathVariable(name = "code") String activationCode) {

        ApiResponse response = userService.activateUser(activationCode);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody CreateUserDTO userDTO) {

        ApiResponse response = userService.create(userDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateUser(
            @PathVariable(name = "id") Long userId,
            @RequestBody UpdateUserDTO userDTO
    ) {

        ApiResponse response = userService.updateUser(userId, userDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}/role")
    public ResponseEntity<ApiResponse> changeRole(
            @PathVariable(name = "id") Long userId,
            @RequestParam(value = "role") String roleName
    ) {

        ApiResponse response = userService.changeRole(userId, roleName);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ApiResponse> changeStatus(
            @PathVariable(name = "id") Long userId,
            @RequestParam(value = "status") String statusName
    ) {

        ApiResponse response = userService.changeStatus(userId, statusName);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
