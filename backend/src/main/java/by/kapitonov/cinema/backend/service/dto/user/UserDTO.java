package by.kapitonov.cinema.backend.service.dto.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class UserDTO {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String role;

}
