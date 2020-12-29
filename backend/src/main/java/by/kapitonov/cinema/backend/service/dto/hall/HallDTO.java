package by.kapitonov.cinema.backend.service.dto.hall;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class HallDTO {

    private Long id;
    private String hallName;
    private String cinemaName;
    private Integer floor;
    private Integer rowsNumbers;
    private Integer numberSeatsPerRow;
    private String status;

}
