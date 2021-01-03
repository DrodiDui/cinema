package by.kapitonov.cinema.backend.service.mapper;

import by.kapitonov.cinema.backend.model.Hall;
import by.kapitonov.cinema.backend.service.dto.hall.HallDTO;

public class HallMapper {

    public static HallDTO toDTO(Hall hall) {
        HallDTO hallDTO = new HallDTO();
        hallDTO.setId(hall.getId());
        hallDTO.setHallName(hall.getHallName());
        hallDTO.setCinemaName(hall.getCinema().getCinemaName());
        hallDTO.setFloor(hall.getFloor());
        hallDTO.setRowsNumbers(hall.getRowsNumbers());
        hallDTO.setNumberSeatsPerRow(hall.getNumberSeatsPerRow());
        hallDTO.setStatus(hall.getStatus().getStatusName());
        return hallDTO;
    }

}
