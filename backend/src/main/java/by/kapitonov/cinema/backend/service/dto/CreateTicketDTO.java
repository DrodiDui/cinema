package by.kapitonov.cinema.backend.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class CreateTicketDTO {

    private Integer rowsNumber;
    private Integer numberSeatsPerRow;
    private Long userId;
    private Long filmSessionId;

}
