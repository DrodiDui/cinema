package by.kapitonov.cinema.backend.service.impl;

import by.kapitonov.cinema.backend.exception.ModelNotFoundException;
import by.kapitonov.cinema.backend.repository.FilmRepository;
import by.kapitonov.cinema.backend.service.CinemaStatusService;
import by.kapitonov.cinema.backend.service.FilmService;
import by.kapitonov.cinema.backend.service.UserService;
import by.kapitonov.cinema.backend.service.dto.film.CreateFilmDTO;
import by.kapitonov.cinema.backend.service.dto.film.FilmDTO;
import by.kapitonov.cinema.backend.service.dto.film.UpdateFilmDTO;
import by.kapitonov.cinema.backend.service.mapper.FilmMapper;
import by.kapitonov.cinema.backend.config.Constants;
import by.kapitonov.cinema.backend.model.CinemaStatus;
import by.kapitonov.cinema.backend.model.Film;
import by.kapitonov.cinema.backend.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;
    private final UserService userService;
    private final CinemaStatusService cinemaStatusService;

    public FilmServiceImpl(
            FilmRepository filmRepository,
            UserService userService,
            CinemaStatusService cinemaStatusService
    ) {
        this.filmRepository = filmRepository;
        this.userService = userService;
        this.cinemaStatusService = cinemaStatusService;
    }

    @Override
    public Page<FilmDTO> getAll(Pageable pageable) {
        return filmRepository.findAll(pageable)
                .map(FilmMapper::toDTO);
    }

    @Override
    public Page<FilmDTO> getAllOwnerFilms(Long ownerId, Pageable pageable) {
        return filmRepository.findByOwnerId(ownerId, pageable)
                .map(FilmMapper::toDTO);
    }

    @Override
    public List<FilmDTO> getAllFilmsByName(String filmName) {
        return filmRepository.findAllByFilmNameContains(filmName)
                .stream()
                .map(FilmMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FilmDTO> getAllOwnerActiveFilms(Long ownerId) {
        return filmRepository.findAllByOwnerIdAndStatusStatusName(ownerId, Constants.ACTIVE_STATUS)
                .stream()
                .map(FilmMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Film getById(Long id) {
        return filmRepository.findById(id)
                .orElseThrow(
                        () -> new ModelNotFoundException("Film hasn't been found by id: " + id)
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
        return filmRepository.save(film);
    }

    @Override
    public Film update(UpdateFilmDTO filmDTO) {
        return filmRepository.findById(filmDTO.getId())
                .map(film -> {
                    film.setFilmName(filmDTO.getFilmName());
                    film.setYearOfRelease(filmDTO.getYearOfRelease());
                    film.setGenre(filmDTO.getGenre());
                    film.setCountryOfOrigin(filmDTO.getCountryOfOrigin());
                    film.setDirector(filmDTO.getDirector());
                    film.setStarring(filmDTO.getStarring());
                    film.setBudget(filmDTO.getBudget());
                    film.setDuration(filmDTO.getDuration());
                    film.setStatus(getStatus(filmDTO.getStatus()));

                    return filmRepository.save(film);
                })
                .orElseThrow(
                        () -> new ModelNotFoundException("Film hasn't been found by id: " + filmDTO.getId())
                );
    }

    @Override
    public Film changeStatus(Long id, String statusName) {
        return filmRepository.findById(id)
                .map(film -> {
                    film.setStatus(getStatus(statusName));
                    return filmRepository.save(film);
                })
                .orElseThrow(
                        () -> new ModelNotFoundException("Film hasn't been found")
                );
    }

    private CinemaStatus getStatus(String statusName) {
        return cinemaStatusService.getByName(statusName);
    }

    private User getOwner(Long id) {
        return userService.getById(id);
    }
}
