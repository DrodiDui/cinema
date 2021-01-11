package by.kapitonov.cinema.backend.service;

import by.kapitonov.cinema.backend.model.FilmSession;
import by.kapitonov.cinema.backend.service.dto.filmsession.CreateFilmSessionDTO;
import by.kapitonov.cinema.backend.service.dto.filmsession.FilmSessionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FilmSessionService {

    Page<FilmSessionDTO> getAll(Pageable pageable);
    Page<FilmSessionDTO> getAllActiveSession(Pageable pageable);

    FilmSession getById(Long id);

    FilmSession create(CreateFilmSessionDTO filmSessionDTO);

}
