package by.kapitonov.cinema.backend.service;

import by.kapitonov.cinema.backend.model.Hall;
import by.kapitonov.cinema.backend.service.dto.hall.CreateHallDTO;
import by.kapitonov.cinema.backend.service.dto.hall.HallDTO;
import by.kapitonov.cinema.backend.service.dto.hall.UpdateHallDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HallService {

    Page<HallDTO> getAll(Pageable pageable);
    Page<HallDTO> getAllByCinemaId(Long cinemaId, Pageable pageable);

    Hall getById(Long id);
    HallDTO getByHallName(String hallName);
    Integer getAllNumberOfSeats(Long id);

    Hall create(CreateHallDTO hallDTO);

    Hall update(UpdateHallDTO hallDTO);
    Hall changStatus(Long id, String statusName);
}
