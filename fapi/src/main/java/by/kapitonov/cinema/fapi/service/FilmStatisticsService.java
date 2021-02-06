package by.kapitonov.cinema.fapi.service;

import by.kapitonov.cinema.fapi.model.statistics.FilmStatistics;
import by.kapitonov.cinema.fapi.rest.response.PageResponse;

import java.util.Map;

public interface FilmStatisticsService {

    PageResponse<FilmStatistics> getReservedTicketsCountByOwnerId(Long ownerId, Map<String, String> pageableParams);

}
