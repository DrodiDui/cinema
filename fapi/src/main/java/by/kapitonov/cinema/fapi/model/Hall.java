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
public class Hall {

    private Long id;
    private String hallName;
    private String cinemaName;
    private Integer floor;
    private Integer rowsNumbers;
    private Integer numberSeatsPerRow;
    private String status;

}
