package by.kapitonov.cinema.backend.service.dto.ticket;

import by.kapitonov.cinema.backend.model.FilmSession;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CreateTicketDTO {

    private Integer rowsNumber;
    private Integer numberSeatsPerRow;
    private FilmSession filmSession;

}
