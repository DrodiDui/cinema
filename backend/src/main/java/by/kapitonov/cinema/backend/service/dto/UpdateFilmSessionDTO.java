package by.kapitonov.cinema.backend.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class UpdateFilmSessionDTO {

    private String filmName;
    private Integer ticketCost;
    private Long hallId;
    private Long managerId;
    private String status;
    private Long filmId;

}
