package by.kapitonov.cinema.backend.model.statistics;

import by.kapitonov.cinema.backend.model.Film;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class FilmStatistics {

    private Film film;
    private Long ticketCount;

}
