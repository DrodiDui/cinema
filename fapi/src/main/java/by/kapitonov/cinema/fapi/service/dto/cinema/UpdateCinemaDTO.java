package by.kapitonov.cinema.fapi.service.dto.cinema;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
