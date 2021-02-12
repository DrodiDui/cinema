package by.kapitonov.cinema.backend.service;

import by.kapitonov.cinema.backend.service.dto.film.statistics.FilmStatisticsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FilmStatisticsService {

    Page<FilmStatisticsDTO> getAllFilmsAndCountOfReservedTicketsByOwnerId(Long ownerId, Pageable pageable);

}
