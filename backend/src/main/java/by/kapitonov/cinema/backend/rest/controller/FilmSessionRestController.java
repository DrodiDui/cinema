package by.kapitonov.cinema.backend.rest.controller;

import by.kapitonov.cinema.backend.model.FilmSession;
import by.kapitonov.cinema.backend.rest.response.ApiResponse;
import by.kapitonov.cinema.backend.service.FilmSessionService;
import by.kapitonov.cinema.backend.service.dto.UpdateFilmSessionDTO;
import by.kapitonov.cinema.backend.service.dto.filmsession.CreateFilmSessionDTO;
import by.kapitonov.cinema.backend.service.dto.filmsession.FilmSessionDTO;
import by.kapitonov.cinema.backend.service.mapper.FilmSessionMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("")
    public ResponseEntity getAll(Pageable pageable) {

        Page<FilmSessionDTO> filmSessionDTOS = filmSessionService.getAll(pageable);

        return new ResponseEntity(filmSessionDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}/active")
    public ResponseEntity getAll(@PathVariable(name = "id") Long hallId, Pageable pageable) {

        Page<FilmSessionDTO> filmSessionDTOS = filmSessionService.getAllActiveSessionByHallId(hallId, pageable);

        return new ResponseEntity(filmSessionDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmSessionDTO> getOne(@PathVariable(name = "id") Long filmSessionId) {

        FilmSession filmSession = filmSessionService.getById(filmSessionId);
        FilmSessionDTO filmSessionDTO = FilmSessionMapper.toDTO(filmSession);

        return new ResponseEntity<>(filmSessionDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody CreateFilmSessionDTO filmSessionDTO) {

        filmSessionService.create(filmSessionDTO);

        return new ResponseEntity<>(new ApiResponse("Film session successfully created"), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable(name = "id") Long filmSessionId,@RequestBody UpdateFilmSessionDTO filmSessionDTO) {

        filmSessionService.update(filmSessionId, filmSessionDTO);

        return new ResponseEntity<>(new ApiResponse("Film session successfully updated"), HttpStatus.OK);
    }
}
