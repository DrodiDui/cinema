package by.kapitonov.cinema.fapi.service.dto.ticket;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@EqualsAndHashCode
public class ReservedTicketDTO {

    private Set<Long> ticketsId;
    private Long userId;
    private String userEmail;

}
