package by.kapitonov.cinema.backend.service.impl;

import by.kapitonov.cinema.backend.exception.ModelNotFoundException;
import by.kapitonov.cinema.backend.model.Film;
import by.kapitonov.cinema.backend.model.FilmSession;
import by.kapitonov.cinema.backend.model.Hall;
import by.kapitonov.cinema.backend.model.Ticket;
import by.kapitonov.cinema.backend.model.User;
import by.kapitonov.cinema.backend.repository.FilmSessionRepository;
import by.kapitonov.cinema.backend.service.CinemaStatusService;
import by.kapitonov.cinema.backend.service.FilmService;
import by.kapitonov.cinema.backend.service.FilmSessionService;
import by.kapitonov.cinema.backend.service.HallService;
import by.kapitonov.cinema.backend.service.TicketService;
import by.kapitonov.cinema.backend.service.UserService;
import by.kapitonov.cinema.backend.service.dto.filmsession.CreateFilmSessionDTO;
import by.kapitonov.cinema.backend.service.dto.filmsession.FilmSessionDTO;
import by.kapitonov.cinema.backend.service.mapper.FilmSessionMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FilmSessionServiceImpl implements FilmSessionService {

    private final FilmSessionRepository filmSessionRepository;
    private final CinemaStatusService cinemaStatusService;
    private final UserService userService;
    private final HallService hallService;
    private final FilmService filmService;
    /*private final TicketService ticketService;*/

    public FilmSessionServiceImpl(
            FilmSessionRepository filmSessionRepository,
            CinemaStatusService cinemaStatusService,
            UserService userService,
            HallService hallService,
            FilmService filmService
            /*TicketService ticketService*/
    ) {
        this.filmSessionRepository = filmSessionRepository;
        this.cinemaStatusService = cinemaStatusService;
        this.userService = userService;
        this.hallService = hallService;
        this.filmService = filmService;
        /*this.ticketService = ticketService;*/
    }

    @Override
    public Page<FilmSessionDTO> getAll(Pageable pageable) {
        return filmSessionRepository.findAll(pageable)
                .map(FilmSessionMapper::toDTO);
    }

    @Override
    public FilmSession getById(Long id) {
        return filmSessionRepository.findById(id)
                .orElseThrow(
                        () -> new ModelNotFoundException("Film session hasn't been found by id: " + id)
                );
    }

    @Override
    public FilmSession create(CreateFilmSessionDTO filmSessionDTO) {
        FilmSession filmSession = new FilmSession();
        filmSession.setTicketCost(filmSessionDTO.getTicketCost());
        filmSession.setShowNumber(UUID.randomUUID().toString());
        filmSession.setFilmName(filmSessionDTO.getFilmName());
        filmSession.setHall(getHall(filmSessionDTO.getHallId()));
        filmSession.setManager(getManager(filmSessionDTO.getManagerId()));
        filmSession.setStatus(cinemaStatusService.getByName(filmSessionDTO.getStatus()));
        filmSession.setTickets(createTickets(filmSessionDTO.getHallId()));
        filmSession.setFilm(getFilm(filmSessionDTO.getFilmId()));

        return filmSessionRepository.save(filmSession);
    }


    private User getManager(Long id) {
        return userService.getById(id);
    }
    
    private Hall getHall(Long id) {
        return hallService.getById(id);
    }

    private Film getFilm(Long id) {
        return filmService.getById(id);
    }

    private List<Ticket> createTickets(Long hallId) {
        List<Ticket> tickets = new ArrayList<>();
        Integer allNumberSeats = hallService.getAllNumberOfSeats(hallId);
        for (int i = 0; i < allNumberSeats; i++) {
            tickets.add(new Ticket());
        }
        return tickets;
    }

}
