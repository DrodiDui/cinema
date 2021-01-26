package by.kapitonov.cinema.backend.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class UpdateUserDTO {

    private String firstName;
    private String lastName;

}
