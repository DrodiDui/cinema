package by.kapitonov.cinema.fapi.rest.controller;

import by.kapitonov.cinema.fapi.model.Film;
import by.kapitonov.cinema.fapi.rest.request.SortRequest;
import by.kapitonov.cinema.fapi.rest.response.ApiResponse;
import by.kapitonov.cinema.fapi.rest.response.PageResponse;
import by.kapitonov.cinema.fapi.service.FilmService;
import by.kapitonov.cinema.fapi.service.dto.CreateFilmDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/films")
public class FilmRestController {

    private final FilmService filmService;

    public FilmRestController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("")
    public ResponseEntity getAll(@RequestParam Map<String, String> pageableParams) {

        PageResponse response = filmService.getAll(pageableParams);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/all/{owner-id}")
    public ResponseEntity getAll(
            @PathVariable(name = "owner-id") Long ownerId,
            @RequestParam Map<String, String> pageableParams) {

        PageResponse response = filmService.getAllOwnerFilms(ownerId, pageableParams);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("{film-name}/all")
    public ResponseEntity getAll(@PathVariable(name = "film-name") String filmName) {

        List<Film> films = filmService.getAllFilmsByName(filmName);

        return new ResponseEntity(films, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity getOne(@PathVariable(name = "id") Long filmId) {

        Film film = filmService.getOne(filmId);

        return new ResponseEntity(film, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody CreateFilmDTO filmDTO) {

        ApiResponse response = filmService.create(filmDTO);

        return new ResponseEntity(response, HttpStatus.OK);
    }

}
