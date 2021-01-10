package by.kapitonov.cinema.fapi.rest.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class SignInRequest {

    private String email;
    private String password;

}
