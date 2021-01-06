package by.kapitonov.cinema.backend.rest.controller;

import by.kapitonov.cinema.backend.rest.response.ApiResponse;
import by.kapitonov.cinema.backend.service.FilmSessionService;
import by.kapitonov.cinema.backend.service.dto.filmsession.CreateFilmSessionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/film-sessions")
public class FilmSessionRestController {

    private final FilmSessionService filmSessionService;

    public FilmSessionRestController(FilmSessionService filmSessionService) {
        this.filmSessionService = filmSessionService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody CreateFilmSessionDTO filmSessionDTO) {

        filmSessionService.create(filmSessionDTO);

        return new ResponseEntity<>(new ApiResponse("Film session successfully created"), HttpStatus.OK);
    }
}
