package by.kapitonov.cinema.fapi.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class CreateFilmSessionDTO {

    private Integer ticketCost;
    private Long hallId;
    private Long managerId;
    private String status;
    private Long filmId;
    private String showTime;

}
