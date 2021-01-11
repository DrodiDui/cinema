package by.kapitonov.cinema.fapi.rest.controller;

import by.kapitonov.cinema.fapi.model.User;
import by.kapitonov.cinema.fapi.rest.response.ApiResponse;
import by.kapitonov.cinema.fapi.rest.request.SignInRequest;
import by.kapitonov.cinema.fapi.rest.response.TokenResponse;
import by.kapitonov.cinema.fapi.security.TokenProvider;
import by.kapitonov.cinema.fapi.service.UserService;
import by.kapitonov.cinema.fapi.service.dto.user.RegistrationUserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {

    private final UserService userService;
    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;

    public AuthRestController(
            UserService userService,
            TokenProvider tokenProvider,
            AuthenticationManager authenticationManager
    ) {
        this.userService = userService;
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("sign-in")
    public ResponseEntity<TokenResponse> login(@RequestBody SignInRequest signInRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInRequest.getEmail(),
                        signInRequest.getPassword()
                )
        );

        String token = tokenProvider.generateToken(authentication);
        User user = userService.getByEmail(signInRequest.getEmail());

        return new ResponseEntity<>(
                new TokenResponse(
                        token,
                        user.getEmail(),
                        user.getRoleName(),
                        user.getId()
                ),
                HttpStatus.OK
        );
    }

    @PostMapping("/sign-up")
    public ResponseEntity<ApiResponse> registration(@RequestBody RegistrationUserDTO userDTO) {

        ApiResponse response = userService.registration(userDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
