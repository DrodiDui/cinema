package by.kapitonov.cinema.backend.service;

import by.kapitonov.cinema.backend.model.Film;
import by.kapitonov.cinema.backend.service.dto.film.CreateFilmDTO;
import by.kapitonov.cinema.backend.service.dto.film.FilmDTO;
import by.kapitonov.cinema.backend.service.dto.film.UpdateFilmDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FilmService {

    Page<FilmDTO> getAll(Pageable pageable);
    Page<FilmDTO> getAllOwnerFilms(Long ownerId, Pageable pageable);

    List<FilmDTO> getAllFilmsByName(String filmName);

    Film getById(Long id);

    Film create(CreateFilmDTO filmDTO);

    Film update(UpdateFilmDTO filmDTO);

    Film changeStatus(Long id, String statusName);
}
