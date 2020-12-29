package by.kapitonov.cinema.backend.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;

@Data
@EqualsAndHashCode
public class TicketDTO {

    private Long id;
    private String filmName;
    private String hallName;
    private Instant showTime;
    private Integer duration;
    private Integer rowsNumber;
    private Integer numberSeatsPerRow;

}
