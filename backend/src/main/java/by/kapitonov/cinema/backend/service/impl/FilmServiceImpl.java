package by.kapitonov.cinema.backend.service.impl;

import by.kapitonov.cinema.backend.exception.ModelNotFoundException;
import by.kapitonov.cinema.backend.model.CinemaStatus;
import by.kapitonov.cinema.backend.model.Film;
import by.kapitonov.cinema.backend.model.User;
import by.kapitonov.cinema.backend.repository.FilmRepository;
import by.kapitonov.cinema.backend.repository.UserRepository;
import by.kapitonov.cinema.backend.service.CinemaStatusService;
import by.kapitonov.cinema.backend.service.FilmService;
import by.kapitonov.cinema.backend.service.dto.film.CreateFilmDTO;
import by.kapitonov.cinema.backend.service.dto.film.FilmDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;
    private final UserRepository userRepository;
    private final CinemaStatusService cinemaStatusService;

    public FilmServiceImpl(
            FilmRepository filmRepository,
            UserRepository userRepository,
            CinemaStatusService cinemaStatusService
    ) {
        this.filmRepository = filmRepository;
        this.userRepository = userRepository;
        this.cinemaStatusService = cinemaStatusService;
    }

    @Override
    public Page<FilmDTO> getAll(Long ownerId, Pageable pageable) {
        return filmRepository.findByOwner_Id(ownerId, pageable)
                .map(film -> {
                    FilmDTO filmDTO = new FilmDTO();
                    filmDTO.setId(film.getId());
                    filmDTO.setFilmName(film.getFilmName());
                    filmDTO.setYearOfRelease(film.getYearOfRelease());
                    filmDTO.setGenre(film.getGenre());
                    filmDTO.setCountryOfOrigin(film.getCountryOfOrigin());
                    filmDTO.setDirector(film.getDirector());
                    filmDTO.setStarring(film.getStarring());
                    filmDTO.setBudget(film.getBudget());
                    filmDTO.setDuration(film.getDuration());
                    filmDTO.setStatus(film.getStatus().getStatusName());
                    return filmDTO;
                });
    }

    @Override
    public FilmDTO getById(Long id) {
        return filmRepository.findById(id)
                .map(film -> {
                    FilmDTO filmDTO = new FilmDTO();
                    filmDTO.setId(film.getId());
                    filmDTO.setFilmName(film.getFilmName());
                    filmDTO.setYearOfRelease(film.getYearOfRelease());
                    filmDTO.setGenre(film.getGenre());
                    filmDTO.setCountryOfOrigin(film.getCountryOfOrigin());
                    filmDTO.setDirector(film.getDirector());
                    filmDTO.setStarring(film.getStarring());
                    filmDTO.setBudget(film.getBudget());
                    filmDTO.setDuration(film.getDuration());
                    filmDTO.setStatus(film.getStatus().getStatusName());
                    return filmDTO;
                })
                .orElseThrow(
                        () -> new ModelNotFoundException("Film hasn't been found")
                );
    }


    @Override
    public Film create(CreateFilmDTO filmDTO) {
        Film film = new Film();
        film.setFilmName(filmDTO.getFilmName());
        film.setYearOfRelease(filmDTO.getYearOfRelease());
        film.setGenre(filmDTO.getGenre());
        film.setCountryOfOrigin(filmDTO.getCountryOfOrigin());
        film.setDirector(filmDTO.getDirector());
        film.setStarring(filmDTO.getStarring());
        film.setBudget(filmDTO.getBudget());
        film.setDuration(filmDTO.getDuration());
        film.setStatus(getStatus(filmDTO.getStatus()));
        film.setOwner(getOwner(filmDTO.getOwnerId()));
        return null;
    }

    private CinemaStatus getStatus(String statusName) {
        return cinemaStatusService.getByName(statusName);
    }

    private User getOwner(Long id) {
        return userRepository.findById(id)
                .orElseThrow(
                        () -> new ModelNotFoundException("User hasn't been found")
                );
    }
}
