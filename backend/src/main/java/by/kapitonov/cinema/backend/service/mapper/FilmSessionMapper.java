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
        filmSessionDTO.setId(filmSession.getId());
        filmSessionDTO.setShowNumber(filmSession.getShowNumber());
        filmSessionDTO.setFilmName(filmSession.getFilmName());
        filmSessionDTO.setTicketCost(filmSession.getTicketCost());
        filmSessionDTO.setCinemaName(filmSession.getHall().getCinema().getCinemaName());
        filmSessionDTO.setHallName(filmSession.getHall().getHallName());
        filmSessionDTO.setStatus(filmSession.getStatus().getStatusName());
        return filmSessionDTO;
    }

}
