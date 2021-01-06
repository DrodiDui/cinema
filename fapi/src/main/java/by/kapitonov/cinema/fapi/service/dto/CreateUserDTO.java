package by.kapitonov.cinema.fapi.service.dto;

import lombok.Data;

@Data
public class CreateUserDTO {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String roleName;
    private String statusName;

}
