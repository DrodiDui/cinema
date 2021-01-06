package by.kapitonov.cinema.fapi.rest.controller;

import by.kapitonov.cinema.fapi.rest.ApiResponse;
import by.kapitonov.cinema.fapi.service.UserService;
import by.kapitonov.cinema.fapi.service.dto.RegistrationUserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {

    private final UserService userService;

    public AuthRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<ApiResponse> registration(@RequestBody RegistrationUserDTO userDTO) {

        userService.registration(userDTO);

        return new ResponseEntity<>(new ApiResponse("User successfully registered"), HttpStatus.OK);
    }
}
