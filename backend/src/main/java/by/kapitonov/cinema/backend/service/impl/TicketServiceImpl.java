package by.kapitonov.cinema.backend.service.impl;

import by.kapitonov.cinema.backend.exception.ModelNotFoundException;
import by.kapitonov.cinema.backend.model.FilmSession;
import by.kapitonov.cinema.backend.model.Ticket;
import by.kapitonov.cinema.backend.model.User;
import by.kapitonov.cinema.backend.repository.TicketRepository;
import by.kapitonov.cinema.backend.service.FilmSessionService;
import by.kapitonov.cinema.backend.service.TicketService;
import by.kapitonov.cinema.backend.service.UserService;
import by.kapitonov.cinema.backend.service.dto.ticket.CreateTicketDTO;
import by.kapitonov.cinema.backend.service.dto.ticket.TicketDTO;
import by.kapitonov.cinema.backend.service.mapper.TicketMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final FilmSessionService filmSessionService;
    private final UserService userService;

    public TicketServiceImpl(
            TicketRepository ticketRepository,
            FilmSessionService filmSessionService,
            UserService userService
    ) {
        this.ticketRepository = ticketRepository;
        this.filmSessionService = filmSessionService;
        this.userService = userService;
    }

    @Override
    public Page<TicketDTO> getUserTickets(Long userId, Pageable pageable) {
        return ticketRepository.findByUserId(userId, pageable)
                .map(TicketMapper::toDTO);
    }

    @Override
    public Ticket unreserved(Long id) {
        return ticketRepository.findById(id)
                .map(ticket -> {
                    ticket.setReserved(false);
                    ticket.setUser(null);
                    return ticketRepository.save(ticket);
                })
                .orElseThrow(
                        () -> new ModelNotFoundException("Ticket hasn't been found by id: " + id)
                );
    }

    @Override
    public Ticket create(CreateTicketDTO ticketDTO) {
        Ticket ticket = new Ticket();
        ticket.setReserved(false);
        ticket.setFilmSession(getFilmSession(ticketDTO.getFilmSessionId()));

        return ticketRepository.save(ticket);
    }

    private User getUser(Long id) {
        return userService.getById(id);
    }

    private FilmSession getFilmSession(Long id) {
        return filmSessionService.getById(id);
    }
}
