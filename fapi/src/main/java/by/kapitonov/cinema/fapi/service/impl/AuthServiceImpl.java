package by.kapitonov.cinema.fapi.service.impl;

import by.kapitonov.cinema.fapi.config.UrlConstants;
import by.kapitonov.cinema.fapi.model.User;
import by.kapitonov.cinema.fapi.rest.response.ApiResponse;
import by.kapitonov.cinema.fapi.rest.response.TokenResponse;
import by.kapitonov.cinema.fapi.security.TokenProvider;
import by.kapitonov.cinema.fapi.service.AuthService;
import by.kapitonov.cinema.fapi.service.dto.user.SignInDTO;
import by.kapitonov.cinema.fapi.service.dto.user.RegistrationUserDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthServiceImpl implements AuthService {

    private final RestTemplate restTemplate;
    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(
            RestTemplateBuilder restTemplateBuilder,
            TokenProvider tokenProvider,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.restTemplate = restTemplateBuilder.rootUri(UrlConstants.USER_URL).build();
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public TokenResponse login(SignInDTO signInDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInDTO.getEmail(),
                        signInDTO.getPassword()
                )
        );

        String token = tokenProvider.generateToken(authentication);
        User user = restTemplate.getForObject("/" + signInDTO.getEmail(), User.class);

        return new TokenResponse(
                token,
                user.getEmail(),
                user.getRoleName(),
                user.getId()
        );
    }

    @Override
    public ApiResponse registration(RegistrationUserDTO registrationUserDTO) {
        String hashPassword = passwordEncoder.encode(registrationUserDTO.getPassword());
        User user = new User();
        user.setEmail(registrationUserDTO.getEmail());
        user.setPassword(hashPassword);
        user.setFirstName(registrationUserDTO.getFirstName());
        user.setLastName(registrationUserDTO.getLastName());

        return restTemplate.postForEntity(UrlConstants.USER_URL + "/registration", user, ApiResponse.class).getBody();
    }
}
