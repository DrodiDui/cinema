package by.kapitonov.cinema.backend.service.dto.ticket;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode
public class UpdateTicketDTO {

    private Set<Long> ticketIds;
    private Long userId;

}
