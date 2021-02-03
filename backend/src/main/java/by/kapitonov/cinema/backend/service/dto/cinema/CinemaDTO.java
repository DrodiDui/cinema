package by.kapitonov.cinema.backend.service.dto.cinema;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class CinemaDTO {

    private Long id;
    private String cinemaName;
    private String country;
    private String city;
    private String address;
    private String description;
    private String creationDate;
    private String status;
    private Long ownerId;
    //private User owner;
    //private List<Hall> halls;

}
