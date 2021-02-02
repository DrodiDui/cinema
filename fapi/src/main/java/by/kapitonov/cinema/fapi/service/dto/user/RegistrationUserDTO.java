package by.kapitonov.cinema.fapi.service.dto.user;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RegistrationUserDTO {

    @Email
    @NotNull
    private String email;

    @Size
    @NotNull
    private String password;

    private String firstName;
    private String lastName;

}
