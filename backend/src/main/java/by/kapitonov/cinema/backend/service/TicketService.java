package by.kapitonov.cinema.backend.service;

import by.kapitonov.cinema.backend.model.Ticket;
import by.kapitonov.cinema.backend.service.dto.CreateTicketDTO;
import by.kapitonov.cinema.backend.service.dto.TicketDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TicketService {

    Page<TicketDTO> getUserTickets(Long userId, Pageable pageable);

    Ticket create(CreateTicketDTO ticketDTO);

    Ticket unreserved(Long id);

}
