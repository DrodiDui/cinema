package by.kapitonov.cinema.fapi.rest.controller;

import by.kapitonov.cinema.fapi.rest.response.ApiResponse;
import by.kapitonov.cinema.fapi.rest.response.TokenResponse;
import by.kapitonov.cinema.fapi.service.AuthService;
import by.kapitonov.cinema.fapi.service.dto.SignInDTO;
import by.kapitonov.cinema.fapi.service.dto.user.RegistrationUserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {

    private final AuthService authService;

    public AuthRestController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("sign-in")
    public ResponseEntity<TokenResponse> login(@RequestBody SignInDTO signInDTO) {

        TokenResponse response = authService.login(signInDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<ApiResponse> registration(@RequestBody RegistrationUserDTO userDTO) {

        ApiResponse response = authService.registration(userDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
