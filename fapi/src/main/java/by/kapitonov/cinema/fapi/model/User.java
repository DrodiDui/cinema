package by.kapitonov.cinema.fapi.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode
public class User implements Serializable {

    private static final Long SERIAL_VERSION = 1L;

    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String roleName;
    private String statusName;
    private Long cinemaId;

}
