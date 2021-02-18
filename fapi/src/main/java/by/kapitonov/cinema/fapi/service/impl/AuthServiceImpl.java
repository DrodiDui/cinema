package by.kapitonov.cinema.fapi.service.impl;

import by.kapitonov.cinema.fapi.config.UrlConstants;
import by.kapitonov.cinema.fapi.exception.AccountAccessException;
import by.kapitonov.cinema.fapi.exception.InvalidEmailOrPasswordException;
import by.kapitonov.cinema.fapi.exception.ModelAlreadyExistsException;
import by.kapitonov.cinema.fapi.model.User;
import by.kapitonov.cinema.fapi.rest.response.ApiResponse;
import by.kapitonov.cinema.fapi.rest.response.TokenResponse;
import by.kapitonov.cinema.fapi.security.TokenProvider;
import by.kapitonov.cinema.fapi.service.AuthService;
import by.kapitonov.cinema.fapi.service.EmailSenderService;
import by.kapitonov.cinema.fapi.service.dto.user.RegistrationUserDTO;
import by.kapitonov.cinema.fapi.service.dto.user.SignInDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    private final RestTemplate restTemplate;
    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final EmailSenderService emailSenderService;

    public AuthServiceImpl(
            RestTemplate restTemplate,
            TokenProvider tokenProvider,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            EmailSenderService emailSenderService
    ) {
        this.restTemplate = restTemplate;
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.emailSenderService = emailSenderService;
    }

    @Override
    public TokenResponse login(SignInDTO signInDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            signInDTO.getEmail(),
                            signInDTO.getPassword()
                    )
            );

            String token = tokenProvider.generateToken(authentication);
            User user = restTemplate.getForObject(UrlConstants.USER_URL + "/" + signInDTO.getEmail(), User.class);


            return new TokenResponse(
                    token,
                    user.getEmail(),
                    user.getRoleName(),
                    user.getId()
            );
        } catch (DisabledException e) {
            throw new AccountAccessException("Your account is inactive");
        } catch (Exception e) {
            throw new InvalidEmailOrPasswordException("Authentication error: invalid email or password");
        }
    }

    @Override
    public ApiResponse registration(RegistrationUserDTO registrationUserDTO) {
        try {

            String hashPassword = passwordEncoder.encode(registrationUserDTO.getPassword());
            User user = new User();
            user.setEmail(registrationUserDTO.getEmail());
            user.setPassword(hashPassword);
            user.setFirstName(registrationUserDTO.getFirstName());
            user.setLastName(registrationUserDTO.getLastName());
            user.setActivationCode(UUID.randomUUID().toString());

            ApiResponse response = restTemplate.postForEntity(
                    UrlConstants.USER_URL + "/registration",
                    user,
                    ApiResponse.class).getBody();

            if (response.getMessage() != null) {
                emailSenderService.sendActivationEmail(user.getEmail(), user.getActivationCode());
            }

            return response;
        } catch (ModelAlreadyExistsException ex) {
            throw new ModelAlreadyExistsException(
                    "User with email " + registrationUserDTO.getEmail() + " already exists"
            );
        }
    }
}
