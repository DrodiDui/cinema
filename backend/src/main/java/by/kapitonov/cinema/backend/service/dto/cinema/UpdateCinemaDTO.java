package by.kapitonov.cinema.backend.service.dto.cinema;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode
public class UpdateCinemaDTO {

    private Long id;
    private String cinemaName;
    private String country;
    private String city;
    private String address;
    private String description;


}
