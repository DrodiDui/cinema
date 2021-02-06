package by.kapitonov.cinema.backend.service.dto.filmsession;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class FilmSessionDTO {

    private Long id;
    private String showNumber;
    private String filmName;
    private Integer ticketCost;
    private String cinemaName;
    private String hallName;
    private String status;
    private String showTime;

}
