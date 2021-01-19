package by.kapitonov.cinema.fapi.service.dto.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class SignInDTO {

    private String email;
    private String password;

}
