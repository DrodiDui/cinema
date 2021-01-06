package by.kapitonov.cinema.fapi.rest.controller;

import by.kapitonov.cinema.fapi.model.User;
import by.kapitonov.cinema.fapi.rest.ApiResponse;
import by.kapitonov.cinema.fapi.service.UserService;
import by.kapitonov.cinema.fapi.service.dto.CreateUserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{email}")
    public ResponseEntity<User> getOne(@PathVariable(name = "email") String email) {

        User user = userService.getByEmail(email);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse> create(@RequestBody CreateUserDTO userDTO) {

        userService.create(userDTO);

        return new ResponseEntity<>(new ApiResponse("User successfully created"), HttpStatus.OK);
    }
}
