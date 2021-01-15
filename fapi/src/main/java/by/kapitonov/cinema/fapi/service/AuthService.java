package by.kapitonov.cinema.fapi.service;

import by.kapitonov.cinema.fapi.rest.response.ApiResponse;
import by.kapitonov.cinema.fapi.rest.response.TokenResponse;
import by.kapitonov.cinema.fapi.service.dto.SignInDTO;
import by.kapitonov.cinema.fapi.service.dto.user.RegistrationUserDTO;

public interface AuthService {
    
    TokenResponse login(SignInDTO signInDTO);

    ApiResponse registration(RegistrationUserDTO registrationUserDTO);
    
}
