package by.kapitonov.cinema.backend.service.impl;

import by.kapitonov.cinema.backend.exception.ModelAlreadyExistsException;
import by.kapitonov.cinema.backend.exception.ModelNotFoundException;
import by.kapitonov.cinema.backend.model.CinemaStatus;
import by.kapitonov.cinema.backend.repository.FilmSessionRepository;
import by.kapitonov.cinema.backend.service.dto.UpdateFilmSessionDTO;
import by.kapitonov.cinema.backend.service.mapper.FilmSessionMapper;
import by.kapitonov.cinema.backend.config.Constants;
import by.kapitonov.cinema.backend.model.Film;
import by.kapitonov.cinema.backend.model.FilmSession;
import by.kapitonov.cinema.backend.model.Hall;
import by.kapitonov.cinema.backend.model.User;
import by.kapitonov.cinema.backend.service.CinemaStatusService;
import by.kapitonov.cinema.backend.service.FilmService;
import by.kapitonov.cinema.backend.service.FilmSessionService;
import by.kapitonov.cinema.backend.service.HallService;
import by.kapitonov.cinema.backend.service.TicketService;
import by.kapitonov.cinema.backend.service.UserService;
import by.kapitonov.cinema.backend.service.dto.filmsession.CreateFilmSessionDTO;
import by.kapitonov.cinema.backend.service.dto.filmsession.FilmSessionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class FilmSessionServiceImpl implements FilmSessionService {

    private final FilmSessionRepository filmSessionRepository;
    private final CinemaStatusService cinemaStatusService;
    private final UserService userService;
    private final HallService hallService;
    private final FilmService filmService;
    private final TicketService ticketService;

    public FilmSessionServiceImpl(
            FilmSessionRepository filmSessionRepository,
            CinemaStatusService cinemaStatusService,
            UserService userService,
            HallService hallService,
            FilmService filmService,
            TicketService ticketService
    ) {
        this.filmSessionRepository = filmSessionRepository;
        this.cinemaStatusService = cinemaStatusService;
        this.userService = userService;
        this.hallService = hallService;
        this.filmService = filmService;
        this.ticketService = ticketService;
    }

    @Override
    public Page<FilmSessionDTO> getAll(Pageable pageable) {
        return filmSessionRepository.findAll(pageable)
                .map(FilmSessionMapper::toDTO);
    }

    @Override
    public Page<FilmSessionDTO> getAllActiveSessionByHallId(Long hallId, Pageable pageable) {
        return filmSessionRepository.findAllByStatusStatusNameAndHallId(Constants.ACTIVE_STATUS, hallId, pageable)
                .map(FilmSessionMapper::toDTO);
    }

    @Override
    public Page<FilmSessionDTO> getAllSessionsByHallId(Long hallId, Pageable pageable) {
        return filmSessionRepository.findAllByHallId(hallId, pageable)
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

        if (
                filmSessionRepository
                        .findFilmSessionByShowTimeAndHallId(
                                stringToInstant(filmSessionDTO.getShowTime()),
                                filmSessionDTO.getHallId()
                        ).isPresent()
        ) {
            throw new ModelAlreadyExistsException("Film session already exists");
        }

        FilmSession filmSession = new FilmSession();
        filmSession.setTicketCost(filmSessionDTO.getTicketCost());
        filmSession.setShowNumber(UUID.randomUUID().toString());
        filmSession.setShowTime(stringToInstant(filmSessionDTO.getShowTime()));
        filmSession.setHall(getHall(filmSessionDTO.getHallId()));
        filmSession.setManager(getManager(filmSessionDTO.getManagerId()));
        filmSession.setStatus(getStatus(filmSessionDTO.getStatus()));
        Film film = getFilm(filmSessionDTO.getFilmId());
        filmSession.setFilm(film);
        filmSession.setFilmName(film.getFilmName());

        filmSession =  filmSessionRepository.save(filmSession);

        createTickets(filmSession);

        return filmSession;
    }

    @Override
    public FilmSession update(Long filmSessionId, UpdateFilmSessionDTO filmSessionDTO) {
        return filmSessionRepository.findById(filmSessionId)
                .map(filmSession -> {
                    filmSession.setFilmName(filmSessionDTO.getFilmName());
                    return filmSessionRepository.save(filmSession);
                })
                .orElseThrow(
                        () -> new ModelNotFoundException("Film session hasn't been found by id: " + filmSessionId)
                );
    }

    @Override
    public FilmSession changeStatus(Long filmSessionId, String statusName) {
        return filmSessionRepository.findById(filmSessionId)
                .map(filmSession -> {
                    filmSession.setStatus(getStatus(statusName));
                    return filmSessionRepository.save(filmSession);
                })
                .orElseThrow(
                        () -> new ModelNotFoundException("Film session hasn't been found by id: " + filmSessionId)
                );
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

    private void createTickets(FilmSession filmSession) {
        ticketService.createTickets(filmSession);
    }

    private Instant stringToInstant(String date) {
        return LocalDateTime.parse(
                date,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        ).toInstant(ZoneOffset.UTC);
    }

    private CinemaStatus getStatus(String statusName) {
        return cinemaStatusService.getByName(statusName);
    }
}
