package by.kapitonov.cinema.backend.repository;

import by.kapitonov.cinema.backend.model.statistics.FilmStatistics;

import java.util.List;

public interface FilmStatisticRepository {

    List<FilmStatistics> findAllCountOfReservedTicketsById(Long ownerId);

}
