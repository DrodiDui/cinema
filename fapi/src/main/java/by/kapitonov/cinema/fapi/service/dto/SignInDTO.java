package by.kapitonov.cinema.fapi.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class SignInDTO {

    private String email;
    private String password;

}
