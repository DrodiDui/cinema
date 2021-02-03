package by.kapitonov.cinema.fapi.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Cinema {

    private Long id;
    private String cinemaName;
    private String country;
    private String city;
    private String address;
    private String description;
    private String creationDate;
    private String status;
    private Long ownerId;

}
