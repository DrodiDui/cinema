package by.kapitonov.cinema.backend.service;

import by.kapitonov.cinema.backend.model.FilmSession;
import by.kapitonov.cinema.backend.model.Ticket;
import by.kapitonov.cinema.backend.service.dto.ticket.UpdateTicketDTO;
import by.kapitonov.cinema.backend.service.dto.ticket.TicketDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TicketService {

    Page<TicketDTO> getUserTickets(Long userId, Pageable pageable);

    Page<TicketDTO> getAllUnreservedTickets(Long sessionId, Pageable pageable);

    List<Ticket> createTickets(FilmSession filmSession);

    List<Ticket> reservedTickets(UpdateTicketDTO ticketDTO);

    List<Ticket> unreservedTickets(List<Long> ticketsId);
    Ticket unreservedOne(Long ticketId);
}
