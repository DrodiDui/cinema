package by.kapitonov.cinema.fapi.rest.controller;

import by.kapitonov.cinema.fapi.model.User;
import by.kapitonov.cinema.fapi.rest.response.ApiResponse;
import by.kapitonov.cinema.fapi.rest.response.PageResponse;
import by.kapitonov.cinema.fapi.service.UserService;
import by.kapitonov.cinema.fapi.service.dto.user.CreateUserDTO;
import org.springframework.data.domain.Page;
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

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity getAll(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size) {

        PageResponse<User> users = userService.getAll(page, size);

        return new ResponseEntity(users, HttpStatus.OK);
    }

    @GetMapping("/{email}")
    public ResponseEntity<User> getOne(@PathVariable(name = "email") String email) {

        User user = userService.getByEmail(email);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse> create(@RequestBody CreateUserDTO userDTO) {

        ApiResponse response = userService.create(userDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}/role")
    public ResponseEntity<ApiResponse> changeRole(
            @PathVariable(name = "id") Long userId,
            @PathParam(value = "role") String roleName
    ) {

        ApiResponse response = userService.changeRole(userId, roleName);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ApiResponse> changeStatus(
            @PathVariable(name = "id") Long userId,
            @PathParam(value = "status") String statusName
    ) {

        ApiResponse response = userService.changeStatus(userId, statusName);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
