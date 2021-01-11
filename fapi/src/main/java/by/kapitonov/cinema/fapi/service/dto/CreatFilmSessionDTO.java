package by.kapitonov.cinema.fapi.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class CreatFilmSessionDTO {

    private String filmName;
    private Integer ticketCost;
    private Long hallId;
    private Long managerId;
    private String status;
    private Long filmId;

}
