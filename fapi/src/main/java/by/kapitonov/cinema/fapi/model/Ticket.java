package by.kapitonov.cinema.fapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.Instant;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Ticket{

    private Long id;
    private String filmName;
    private String hallName;
    private Instant showTime;
    private Integer duration;
    private Integer rowsNumber;
    private Integer numberSeatsPerRow;

}