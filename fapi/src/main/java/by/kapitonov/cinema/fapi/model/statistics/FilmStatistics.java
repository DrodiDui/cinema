package by.kapitonov.cinema.fapi.model.statistics;

import by.kapitonov.cinema.fapi.model.Film;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class FilmStatistics {

    private Film film;
    private Long ticketCount;

}
