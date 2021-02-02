package by.kapitonov.cinema.fapi.service.dto.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode
public class SignInDTO {

    @Email
    @NotNull
    private String email;

    @NotNull
    @Size(min = 6, max = 10)
    private String password;

}
