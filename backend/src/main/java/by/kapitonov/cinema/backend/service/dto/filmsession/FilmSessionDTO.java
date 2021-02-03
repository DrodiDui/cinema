package by.kapitonov.cinema.backend.service.dto.filmsession;

import by.kapitonov.cinema.backend.model.CinemaStatus;
import by.kapitonov.cinema.backend.model.Hall;
import by.kapitonov.cinema.backend.model.Ticket;
import by.kapitonov.cinema.backend.model.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

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
