package by.kapitonov.cinema.backend.service.impl;

import by.kapitonov.cinema.backend.exception.ModelNotFoundException;
import by.kapitonov.cinema.backend.repository.TicketRepository;
import by.kapitonov.cinema.backend.service.TicketService;
import by.kapitonov.cinema.backend.service.UserService;
import by.kapitonov.cinema.backend.service.mapper.TicketMapper;
import by.kapitonov.cinema.backend.model.FilmSession;
import by.kapitonov.cinema.backend.model.Hall;
import by.kapitonov.cinema.backend.model.Ticket;
import by.kapitonov.cinema.backend.model.User;
import by.kapitonov.cinema.backend.service.dto.ticket.UpdateTicketDTO;
import by.kapitonov.cinema.backend.service.dto.ticket.TicketDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final UserService userService;

    public TicketServiceImpl(
            TicketRepository ticketRepository,
            UserService userService
    ) {
        this.ticketRepository = ticketRepository;
        this.userService = userService;
    }

    @Override
    public Page<TicketDTO> getUserTickets(Long userId, Pageable pageable) {
        return ticketRepository.findByUserId(userId, pageable)
                .map(TicketMapper::toDTO);
    }

    @Override
    public Page<TicketDTO> getAllUnreservedTickets(Long sessionId, Pageable pageable) {
        return ticketRepository.findAllByReservedFalseAndFilmSessionId(sessionId, pageable)
                .map(TicketMapper::toDTO);
    }

    @Override
    public List<Ticket> unreservedTickets(List<Long> ticketsId) {
        return ticketsId
                .stream()
                .map(ticketId -> unreserved(ticketId))
                .collect(Collectors.toList());
    }

    @Override
    public Ticket unreservedOne(Long ticketId) {
        return unreserved(ticketId);
    }

    @Override
    public List<Ticket> reservedTickets(UpdateTicketDTO ticketDTO) {
        if (ticketDTO.getTicketsId().isEmpty()) {
            throw new ModelNotFoundException("List of ticket ids is empty");
        }
        return ticketDTO.getTicketsId()
                .stream()
                .map(ticketId -> reserved(ticketId, ticketDTO.getUserId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Ticket> createTickets(FilmSession filmSession) {
        List<Ticket> tickets = new LinkedList<>();
        Hall hall = filmSession.getHall();
        for (int i = 0; i < hall.getRowsNumbers(); i++) {
            for (int j = 0; j < hall.getNumberSeatsPerRow(); j++) {
                Ticket ticket = new Ticket();
                ticket.setReserved(false);
                ticket.setFilmSession(filmSession);
                ticket.setRowsNumber(new Integer(i + 1));
                ticket.setNumberSeatsPerRow(new Integer(j + 1));
                tickets.add(ticket);
            }
        }

        return ticketRepository.saveAll(tickets);
    }

    private User getUser(Long id) {
        return userService.getById(id);
    }

    private Ticket reserved(Long ticketId, Long userId) {
        return ticketRepository.findById(ticketId)
                .map(ticket -> {
                    ticket.setReserved(true);
                    ticket.setUser(getUser(userId));
                    return ticketRepository.save(ticket);
                })
                .orElseThrow(
                        () -> new ModelNotFoundException("Ticket hasn't been found by id: " + ticketId)
                );
    }

    private Ticket unreserved(Long ticketId) {
        return ticketRepository.findById(ticketId)
                .map(ticket -> {
                    ticket.setReserved(false);
                    ticket.setUser(null);
                    return ticketRepository.save(ticket);
                })
                .orElseThrow(
                        () -> new ModelNotFoundException("Ticket hasn't been found by id: " + ticketId)
                );
    }

}
