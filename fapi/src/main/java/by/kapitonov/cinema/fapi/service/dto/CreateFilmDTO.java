package by.kapitonov.cinema.fapi.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class CreateFilmDTO {

    private String filmName;
    private String yearOfRelease;
    private String genre;
    private String countryOfOrigin;
    private String director;
    private String starring;
    private Integer budget;
    private Integer duration;
    private String status;
    private Long ownerId;
}
