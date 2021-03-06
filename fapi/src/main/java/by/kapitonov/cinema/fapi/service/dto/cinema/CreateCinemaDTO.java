package by.kapitonov.cinema.fapi.service.dto.cinema;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class CreateCinemaDTO {

    private String cinemaName;
    private String country;
    private String city;
    private String address;
    private String description;
    private String statusName;
    private Long ownerId;

}
