package by.kapitonov.cinema.fapi.service.dto.user;

import lombok.Data;

@Data
public class CreateUserDTO {

    private String email;
    private String firstName;
    private String lastName;
    private String roleName;
    private String statusName;
    private Long cinemaId;

}
