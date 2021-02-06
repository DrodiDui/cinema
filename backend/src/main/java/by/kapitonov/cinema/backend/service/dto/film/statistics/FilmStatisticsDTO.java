package by.kapitonov.cinema.backend.service.dto.film.statistics;

import by.kapitonov.cinema.backend.service.dto.film.FilmDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmStatisticsDTO {

    private FilmDTO film;
    private Long ticketsCount;

}
