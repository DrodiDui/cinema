package by.kapitonov.cinema.backend.service.mapper;

import by.kapitonov.cinema.backend.exception.ModelNotFoundException;
import by.kapitonov.cinema.backend.service.dto.film.FilmDTO;
import by.kapitonov.cinema.backend.model.Film;

public class FilmMapper {

    public static FilmDTO toDTO(Film film) {
        if (film == null) {
            throw new ModelNotFoundException("Film not found exception");
        }
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
    }

}
