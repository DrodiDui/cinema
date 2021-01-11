package by.kapitonov.cinema.fapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Film {

    private Long id;
    private String filmName;
    private String yearOfRelease;
    private String genre;
    private String countryOfOrigin;
    private String director;
    private String starring;
    private Integer budget;
    private Integer duration;
    private CinemaStatus status;
    private User owner;
    private List<FilmSession> filmSessions;
}