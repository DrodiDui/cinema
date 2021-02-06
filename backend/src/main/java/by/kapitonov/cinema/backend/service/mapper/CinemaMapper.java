package by.kapitonov.cinema.backend.service.mapper;

import by.kapitonov.cinema.backend.exception.ModelNotFoundException;
import by.kapitonov.cinema.backend.model.Cinema;
import by.kapitonov.cinema.backend.service.dto.cinema.CinemaDTO;

public class CinemaMapper {

    public static CinemaDTO toDTO(Cinema cinema) {
        if (cinema == null) {
            throw new ModelNotFoundException("Cinema hasn't been found");
        }
        CinemaDTO cinemaDTO = new CinemaDTO();
        cinemaDTO.setCinemaName(cinema.getCinemaName());
        cinemaDTO.setId(cinema.getId());
        cinemaDTO.setCountry(cinema.getCountry());
        cinemaDTO.setCity(cinema.getCity());
        cinemaDTO.setAddress(cinema.getAddress());
        cinemaDTO.setCreationDate(cinema.getCreationDate().toString());
        cinemaDTO.setDescription(cinema.getDescription());
        cinemaDTO.setStatus(cinema.getStatus().getStatusName());
        cinemaDTO.setOwnerId(cinema.getOwner().getId());
        return cinemaDTO;
    }

}
