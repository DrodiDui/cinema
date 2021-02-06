package by.kapitonov.cinema.backend.service.mapper;

import by.kapitonov.cinema.backend.model.statistics.FilmStatistics;
import by.kapitonov.cinema.backend.service.dto.film.FilmDTO;
import by.kapitonov.cinema.backend.service.dto.film.statistics.FilmStatisticsDTO;

public class FilmStatisticsMapper {

    public static FilmStatisticsDTO toDTO(FilmStatistics filmStatistics) {
        FilmDTO filmDTO = FilmMapper.toDTO(filmStatistics.getFilm());
        FilmStatisticsDTO filmStatisticsDTO = new FilmStatisticsDTO();
        filmStatisticsDTO.setFilm(filmDTO);
        filmStatisticsDTO.setTicketsCount(filmStatistics.getTicketCount());
        return filmStatisticsDTO;
    }
}
