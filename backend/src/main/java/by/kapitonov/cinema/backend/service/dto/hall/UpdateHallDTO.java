package by.kapitonov.cinema.backend.service.dto.hall;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class UpdateHallDTO {

    private Long id;
    private String hallName;
    private Integer floor;
    private Integer rowsNumbers;
    private Integer numberSeatsPerRow;
    private String status;

}
