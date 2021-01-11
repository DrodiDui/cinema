package by.kapitonov.cinema.backend.service.dto.cinema;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

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