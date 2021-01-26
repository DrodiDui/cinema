package by.kapitonov.cinema.backend.service;

import by.kapitonov.cinema.backend.model.Cinema;
import by.kapitonov.cinema.backend.service.dto.cinema.CinemaDTO;
import by.kapitonov.cinema.backend.service.dto.cinema.CreateCinemaDTO;
import by.kapitonov.cinema.backend.service.dto.cinema.UpdateCinemaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CinemaService {

    Page<CinemaDTO> getAll(Pageable pageable);
    Page<CinemaDTO> getAllByOwnerId(Long ownerId, Pageable pageable);

    List<CinemaDTO> getAllByCountyCityAndOwnerId(String country, String city, Long ownerId);

    CinemaDTO getByName(String cinemaName);
    Cinema getById(Long id);

    Cinema create(CreateCinemaDTO cinemaDTO);
    Cinema update(UpdateCinemaDTO cinemaDTO);

    Cinema changeStatus(Long id, String statusName);

    CinemaDTO getByManagerId(Long managerId);
}
