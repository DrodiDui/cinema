package by.kapitonov.cinema.backend.service;

import by.kapitonov.cinema.backend.model.FilmSession;
import by.kapitonov.cinema.backend.service.dto.UpdateFilmSessionDTO;
import by.kapitonov.cinema.backend.service.dto.filmsession.CreateFilmSessionDTO;
import by.kapitonov.cinema.backend.service.dto.filmsession.FilmSessionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FilmSessionService {

    Page<FilmSessionDTO> getAll(Pageable pageable);
    Page<FilmSessionDTO> getAllActiveSessionByHallId(Long hallId, Pageable pageable);

    FilmSession getById(Long id);

    FilmSession create(CreateFilmSessionDTO filmSessionDTO);

    FilmSession update(Long filmSessionId, UpdateFilmSessionDTO filmSessionDTO);
}
