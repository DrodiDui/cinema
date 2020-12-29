package by.kapitonov.cinema.backend.service.impl;

import by.kapitonov.cinema.backend.exception.ModelNotFoundException;
import by.kapitonov.cinema.backend.model.FilmSession;
import by.kapitonov.cinema.backend.model.Hall;
import by.kapitonov.cinema.backend.model.Ticket;
import by.kapitonov.cinema.backend.model.User;
import by.kapitonov.cinema.backend.repository.FilmRepository;
import by.kapitonov.cinema.backend.repository.FilmSessionRepository;
import by.kapitonov.cinema.backend.repository.HallRepository;
import by.kapitonov.cinema.backend.repository.TicketRepository;
import by.kapitonov.cinema.backend.repository.UserRepository;
import by.kapitonov.cinema.backend.service.CinemaStatusService;
import by.kapitonov.cinema.backend.service.FilmSessionService;
import by.kapitonov.cinema.backend.service.dto.CreateFilmSessionDTO;
import by.kapitonov.cinema.backend.service.dto.FilmSessionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FilmSessionServiceImpl implements FilmSessionService {

    private final FilmSessionRepository filmSessionRepository;
    private final UserRepository userRepository;
    private final HallRepository hallRepository;
    private final FilmRepository filmRepository;
    private final CinemaStatusService cinemaStatusService;

    public FilmSessionServiceImpl(
            FilmSessionRepository filmSessionRepository,
            UserRepository userRepository,
            HallRepository hallRepository,
            FilmRepository filmRepository,
            CinemaStatusService cinemaStatusService
    ) {
        this.filmSessionRepository = filmSessionRepository;
        this.userRepository = userRepository;
        this.hallRepository = hallRepository;
        this.filmRepository = filmRepository;
        this.cinemaStatusService = cinemaStatusService;
    }

    @Override
    public Page<FilmSessionDTO> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public FilmSession create(CreateFilmSessionDTO filmSessionDTO) {
        FilmSession filmSession = new FilmSession();
        filmSession.setShowNumber(UUID.randomUUID().toString());
        filmSession.setFilmName(filmSessionDTO.getFilmName());
        filmSession.setHall(getHall(filmSessionDTO.getHallId()));
        filmSession.setManager(getManager(filmSessionDTO.getManagerId()));
        filmSession.setStatus(cinemaStatusService.getByName(filmSessionDTO.getStatus()));
        filmSession.setTickets(createTickets(filmSessionDTO.getHallId()));

        return filmSessionRepository.save(filmSession);
    }


    private User getManager(Long id) {
        return userRepository.findById(id)
                .orElseThrow(
                        () -> new ModelNotFoundException("User hasn't been found")
                );
    }
    
    private Hall getHall(Long id) {
        return hallRepository.findById(id)
                .orElseThrow(
                        () -> new ModelNotFoundException("Hall hasn't been found")
                );
    }

    private List<Ticket> createTickets(Long hallId) {
        List<Ticket> tickets = new ArrayList<>();
        Hall hall = getHall(hallId);
        Integer allNumberSeats = hall.getRowsNumbers() * hall.getNumberSeatsPerRow();
        for (int i = 0; i < allNumberSeats; i++) {
            tickets.add(new Ticket());
        }
        return tickets;
    }

}
