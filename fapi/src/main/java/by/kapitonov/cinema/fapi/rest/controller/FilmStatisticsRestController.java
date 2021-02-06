package by.kapitonov.cinema.fapi.rest.controller;

import by.kapitonov.cinema.fapi.rest.response.PageResponse;
import by.kapitonov.cinema.fapi.service.FilmStatisticsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/statistics/films")
public class FilmStatisticsRestController {

    private final FilmStatisticsService filmStatisticsService;

    public FilmStatisticsRestController(FilmStatisticsService filmStatisticsService) {
        this.filmStatisticsService = filmStatisticsService;
    }

    @GetMapping("/{id}")
    public ResponseEntity getAll(
            @PathVariable(name = "id") Long ownerId,
            @RequestParam Map<String, String> pageableParam
    ) {

        PageResponse response = filmStatisticsService.getReservedTicketsCountByOwnerId(ownerId, pageableParam);

        return new ResponseEntity(response, HttpStatus.OK);
    }
}
