package by.kapitonov.cinema.fapi.service.dto.user;

import lombok.Data;

@Data
public class RegistrationUserDTO {

    private String email;
    private String password;
    private String firstName;
    private String lastName;

}
