package by.kapitonov.cinema.backend.service.impl;

import by.kapitonov.cinema.backend.exception.ModelNotFoundException;
import by.kapitonov.cinema.backend.model.FilmSession;
import by.kapitonov.cinema.backend.model.Ticket;
import by.kapitonov.cinema.backend.model.User;
import by.kapitonov.cinema.backend.repository.FilmSessionRepository;
import by.kapitonov.cinema.backend.repository.TicketRepository;
import by.kapitonov.cinema.backend.repository.UserRepository;
import by.kapitonov.cinema.backend.service.TicketService;
import by.kapitonov.cinema.backend.service.dto.CreateTicketDTO;
import by.kapitonov.cinema.backend.service.dto.TicketDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final FilmSessionRepository filmSessionRepository;
    private final UserRepository userRepository;

    public TicketServiceImpl(
            TicketRepository ticketRepository,
            FilmSessionRepository filmSessionRepository,
            UserRepository userRepository
    ) {
        this.ticketRepository = ticketRepository;
        this.filmSessionRepository = filmSessionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Page<TicketDTO> getUserTickets(Long userId, Pageable pageable) {
        return ticketRepository.findByUser_Id(userId, pageable)
                .map(ticket -> {
                    TicketDTO ticketDTO = new TicketDTO();
                    ticketDTO.setId(ticket.getId());
                    ticketDTO.setRowsNumber(ticket.getRowsNumber());
                    ticketDTO.setNumberSeatsPerRow(ticket.getNumberSeatsPerRow());
                    ticketDTO.setFilmName(ticket.getFilmSession().getFilmName());
                    ticketDTO.setHallName(ticket.getFilmSession().getHall().getHallName());
                    ticketDTO.setShowTime(ticket.getFilmSession().getShowTime());
                    ticketDTO.setDuration(ticket.getFilmSession().getFilm().getDuration());

                    return ticketDTO;
                });
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
        ticket.setUser(getUser(ticketDTO.getUserId()));
        ticket.setRowsNumber(ticketDTO.getRowsNumber());
        ticket.setNumberSeatsPerRow(ticketDTO.getNumberSeatsPerRow());

        return ticketRepository.save(ticket);
    }

    private User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(
                        () -> new ModelNotFoundException("User hasn't been found")
                );
    }

    private FilmSession getFilmSession(Long id) {
        return filmSessionRepository.findById(id)
                .orElseThrow(
                        () -> new ModelNotFoundException("Film session hasn't been found")
                );
    }
}
