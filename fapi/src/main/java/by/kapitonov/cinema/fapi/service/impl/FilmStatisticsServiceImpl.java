package by.kapitonov.cinema.fapi.service.impl;

import by.kapitonov.cinema.fapi.config.UrlConstants;
import by.kapitonov.cinema.fapi.model.statistics.FilmStatistics;
import by.kapitonov.cinema.fapi.rest.response.PageResponse;
import by.kapitonov.cinema.fapi.service.FilmStatisticsService;
import by.kapitonov.cinema.fapi.service.mapper.UrlMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class FilmStatisticsServiceImpl implements FilmStatisticsService {

    private final RestTemplate restTemplate;

    public FilmStatisticsServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public PageResponse<FilmStatistics> getReservedTicketsCountByOwnerId(Long ownerId, Map<String, String> pageableParams) {
        String url = UrlMapper.mapPramsToUrlWithParams(UrlConstants.FILM_STATISTIC_URL + "/" + ownerId, pageableParams);

        return restTemplate.getForObject(
                url,
                PageResponse.class
        );
    }
}
