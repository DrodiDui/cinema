package by.kapitonov.cinema.fapi.rest.controller;

import by.kapitonov.cinema.fapi.model.FilmSession;
import by.kapitonov.cinema.fapi.rest.response.PageResponse;
import by.kapitonov.cinema.fapi.service.FilmSessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/api/film-sessions")
public class FilmSessionRestController {

    private final FilmSessionService filmSessionService;

    public FilmSessionRestController(FilmSessionService filmSessionService) {
        this.filmSessionService = filmSessionService;
    }

    @GetMapping("/{id}/active")
    public ResponseEntity getAll(
            @PathVariable(name = "id") Long hallId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        PageResponse<FilmSession> filmSessionPageResponse =
                filmSessionService.getAllActiveSessionByHallId(hallId, page, size);

        return new ResponseEntity(filmSessionPageResponse, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<FilmSession> getOne(@PathVariable(name = "id") Long hallId) {

        FilmSession filmSession = filmSessionService.getOne(hallId);

        return new ResponseEntity<>(filmSession, HttpStatus.OK);
    }

}
