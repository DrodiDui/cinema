package by.kapitonov.cinema.backend.service.mapper;

import by.kapitonov.cinema.backend.exception.ModelNotFoundException;
import by.kapitonov.cinema.backend.model.FilmSession;
import by.kapitonov.cinema.backend.service.dto.filmsession.FilmSessionDTO;

public class FilmSessionMapper {

    public static FilmSessionDTO toDTO(FilmSession filmSession) {
        if (filmSession == null) {
            throw new ModelNotFoundException("Film session hasn't been found");
        }
        FilmSessionDTO filmSessionDTO = new FilmSessionDTO();
        return filmSessionDTO;
    }

}
