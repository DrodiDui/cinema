package by.kapitonov.cinema.backend.service;

import by.kapitonov.cinema.backend.model.Film;
import by.kapitonov.cinema.backend.service.dto.film.CreateFilmDTO;
import by.kapitonov.cinema.backend.service.dto.film.FilmDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FilmService {

    Page<FilmDTO> getAll(Long ownerId, Pageable pageable);

    FilmDTO getById(Long id);

    Film create(CreateFilmDTO filmDTO);

}
