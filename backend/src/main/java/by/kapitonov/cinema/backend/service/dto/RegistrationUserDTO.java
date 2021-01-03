package by.kapitonov.cinema.backend.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class RegistrationUserDTO {

    private String email;
    private String password;
    private String firstName;
    private String lastName;

}
