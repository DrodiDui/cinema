package by.kapitonov.cinema.backend.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;

@Data
@EqualsAndHashCode
public class CreateFilmSessionDTO {

    private String filmName;
    private Integer ticketCost;
    private Instant startFilmTime;
    private Long hallId;
    private Long managerId;
    private String status;
    //private List<Ticket> tickets;
}
