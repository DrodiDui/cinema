package by.kapitonov.cinema.backend.rest.controller;

import by.kapitonov.cinema.backend.service.FilmStatisticsService;
import by.kapitonov.cinema.backend.service.dto.film.statistics.FilmStatisticsDTO;
import by.kapitonov.cinema.backend.service.mapper.PageableMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/statistics/films")
public class FilmStatisticsRestController {

    private final FilmStatisticsService filmStatisticsService;

    public FilmStatisticsRestController(FilmStatisticsService filmStatisticsService) {
        this.filmStatisticsService = filmStatisticsService;
    }


    @GetMapping("/{id}")
    public ResponseEntity getStatistics(
            @PathVariable(name = "id") Long ownerId,
            @RequestParam Map<String, String> pageableParams) {

        Pageable pageable = PageableMapper.mapToPageable(pageableParams);

        Page<FilmStatisticsDTO> filmStatisticsDTOS =
                filmStatisticsService.getAllFilmsAndCountOfReservedTicketsByOwnerId(ownerId, pageable);

        return new ResponseEntity(filmStatisticsDTOS, HttpStatus.OK);
    }

}
