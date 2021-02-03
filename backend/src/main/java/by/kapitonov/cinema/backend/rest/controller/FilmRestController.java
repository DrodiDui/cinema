package by.kapitonov.cinema.backend.rest.controller;

import by.kapitonov.cinema.backend.model.Film;
import by.kapitonov.cinema.backend.rest.SortRequest;
import by.kapitonov.cinema.backend.rest.response.ApiResponse;
import by.kapitonov.cinema.backend.service.FilmService;
import by.kapitonov.cinema.backend.service.dto.film.CreateFilmDTO;
import by.kapitonov.cinema.backend.service.dto.film.FilmDTO;
import by.kapitonov.cinema.backend.service.dto.film.UpdateFilmDTO;
import by.kapitonov.cinema.backend.service.mapper.FilmMapper;
import by.kapitonov.cinema.backend.service.mapper.PageableMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping
    public ResponseEntity getAll(@RequestParam Map<String, String> pageableParams) {

        Pageable pageable = PageableMapper.mapToPageable(pageableParams);

        Page<FilmDTO> filmDTOS = filmService.getAll(pageable);

        return new ResponseEntity(filmDTOS, HttpStatus.OK);
    }

    @GetMapping("/all/{owner-id}")
    public ResponseEntity getAll(
            @PathVariable(name = "owner-id") Long ownerId,
            @RequestParam Map<String, String> pageableParams
    ) {

        Pageable pageable = PageableMapper.mapToPageable(pageableParams);

        Page<FilmDTO> filmDTOS = filmService.getAllOwnerFilms(ownerId, pageable);

        return new ResponseEntity(filmDTOS, HttpStatus.OK);
    }

    @GetMapping("/all/{owner-id}/active")
    public ResponseEntity getAll(@PathVariable(name = "owner-id") Long ownerId) {

        List<FilmDTO> filmDTOS = filmService.getAllOwnerActiveFilms(ownerId);
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

    @PatchMapping
    public ResponseEntity<ApiResponse> update(@RequestBody UpdateFilmDTO filmDTO) {

        filmService.update(filmDTO);

        return new ResponseEntity<>(new ApiResponse("Film successfully updated"), HttpStatus.OK);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse> updateStatus(
            @PathVariable(name = "id") Long id,
            @RequestParam(value = "status") String statusName
    ) {

        filmService.changeStatus(id, statusName);

        return new ResponseEntity<>(new ApiResponse("Film status successfully updated"), HttpStatus.OK);
    }
}
