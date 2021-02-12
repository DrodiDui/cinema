package by.kapitonov.cinema.backend.service.impl;

import by.kapitonov.cinema.backend.repository.FilmStatisticRepository;
import by.kapitonov.cinema.backend.service.FilmStatisticsService;
import by.kapitonov.cinema.backend.service.dto.film.statistics.FilmStatisticsDTO;
import by.kapitonov.cinema.backend.model.Film;
import by.kapitonov.cinema.backend.model.FilmSession;
import by.kapitonov.cinema.backend.model.Ticket;
import by.kapitonov.cinema.backend.service.mapper.FilmStatisticsMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmStatisticsServiceImpl implements FilmStatisticsService {

    private final FilmStatisticRepository filmStatisticRepository;

    public FilmStatisticsServiceImpl(FilmStatisticRepository filmStatisticRepository) {
        this.filmStatisticRepository = filmStatisticRepository;
    }

    @Override
    public Page<FilmStatisticsDTO> getAllFilmsAndCountOfReservedTicketsByOwnerId(Long ownerId, Pageable pageable) {
        List<FilmStatisticsDTO> filmStatisticsDTOS = filmStatisticRepository
                .findAllCountOfReservedTicketsById(ownerId)
                .stream()
                .map(FilmStatisticsMapper::toDTO)
                .collect(Collectors.toList());

        int start = Math.toIntExact(pageable.getOffset());
        int end = Math.toIntExact(Math.min((start + pageable.getPageSize()), filmStatisticsDTOS.size()));
        return new PageImpl<>(filmStatisticsDTOS.subList(start, end), pageable, filmStatisticsDTOS.size());
    }
}
