package by.kapitonov.cinema.fapi.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode
public class Cinema {

    private Long id;
    private String cinemaName;
    private String country;
    private String city;
    private String address;
    private String description;
    private Date creationDate;
    private String status;

}
