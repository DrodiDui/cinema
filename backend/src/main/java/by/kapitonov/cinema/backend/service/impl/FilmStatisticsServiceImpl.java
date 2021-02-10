package by.kapitonov.cinema.backend.service.impl;

import by.kapitonov.cinema.backend.repository.FilmRepository;
import by.kapitonov.cinema.backend.service.FilmStatisticsService;
import by.kapitonov.cinema.backend.service.dto.film.statistics.FilmStatisticsDTO;
import by.kapitonov.cinema.backend.model.Film;
import by.kapitonov.cinema.backend.model.FilmSession;
import by.kapitonov.cinema.backend.model.Ticket;
import by.kapitonov.cinema.backend.model.statistics.FilmStatistics;
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

    private final FilmRepository filmRepository;

    public FilmStatisticsServiceImpl(
            FilmRepository filmRepository
    ) {
        this.filmRepository = filmRepository;
    }

    @Override
    public Page<FilmStatisticsDTO> getAllCountOfReservedTicketsById(Long ownerId, Pageable pageable) {
        List<FilmStatisticsDTO> filmStatisticsDTOS = filmRepository.findByOwnerId(ownerId)
                .stream()
                .map(film -> {
                    Long ticketCount = getReservedTicketCount(film);
                    return new FilmStatistics(film, ticketCount);
                })
                .map(FilmStatisticsMapper::toDTO)
                .collect(Collectors.toList());

        int start = Math.toIntExact(pageable.getOffset());
        int end = Math.toIntExact(Math.min((start + pageable.getPageSize()), filmStatisticsDTOS.size()));
        return new PageImpl<>(filmStatisticsDTOS.subList(start, end), pageable, filmStatisticsDTOS.size());

        /*return new PageImpl<FilmStatisticsDTO>(filmStatisticsDTOS, pageable, filmStatisticsDTOS.size());*/
    }

    private Long getReservedTicketCount(Film film) {
        List<Ticket> tickets = new ArrayList<>();
        for (FilmSession filmSession : film.getFilmSessions()) {
            tickets.addAll(filmSession.getTickets());
        }
        return tickets.stream()
                .filter(Ticket::getReserved)
                .count();
    }
}
