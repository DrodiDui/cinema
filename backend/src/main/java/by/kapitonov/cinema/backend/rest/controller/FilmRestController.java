package by.kapitonov.cinema.backend.rest.controller;

import by.kapitonov.cinema.backend.model.Film;
import by.kapitonov.cinema.backend.rest.response.ApiResponse;
import by.kapitonov.cinema.backend.service.FilmService;
import by.kapitonov.cinema.backend.service.dto.film.CreateFilmDTO;
import by.kapitonov.cinema.backend.service.dto.film.FilmDTO;
import by.kapitonov.cinema.backend.service.mapper.FilmMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/films")
public class FilmRestController {

    private final FilmService filmService;

    public FilmRestController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/{owner-id}/all")
    public ResponseEntity getAll(@PathVariable(name = "owner-id") Long ownerId, Pageable pageable) {

        Page<FilmDTO> filmDTOS = filmService.getAll(ownerId, pageable);

        return new ResponseEntity(filmDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmDTO> getOne(@PathVariable(name = "id") Long id) {

        Film film = filmService.getById(id);
        FilmDTO filmDTO = FilmMapper.toDTO(film);

        return new ResponseEntity<>(filmDTO, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse> create(@RequestBody CreateFilmDTO filmDTO) {

        filmService.create(filmDTO);

        return new ResponseEntity<>(new ApiResponse("Film successfully created"), HttpStatus.OK);
    }
}
