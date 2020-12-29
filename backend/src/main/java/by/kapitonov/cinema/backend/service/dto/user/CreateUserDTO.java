package by.kapitonov.cinema.backend.service.dto.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class CreateUserDTO {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String roleName;
    private String statusName;

}
