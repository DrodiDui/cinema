package by.kapitonov.cinema.fapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class FilmSession{

    private Long id;
    private String showNumber;
    private String filmName;
    private Integer ticketCost;
    private String cinemaName;
    private String hallName;
    private String status;
}