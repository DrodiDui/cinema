package by.kapitonov.cinema.fapi.security;

import by.kapitonov.cinema.fapi.config.UrlConstants;
import by.kapitonov.cinema.fapi.exception.ModelNotFoundException;
import by.kapitonov.cinema.fapi.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final RestTemplate restTemplate;

    public CustomUserDetailsService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = restTemplate.getForObject(UrlConstants.USER_URL + "/" + email, User.class);

        if (user == null) {
            throw new ModelNotFoundException("User hasn't been found by email: " + email);
        }

        return new UserDetailsImpl(user);
    }
}
