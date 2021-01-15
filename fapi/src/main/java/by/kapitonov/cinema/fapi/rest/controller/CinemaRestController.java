package by.kapitonov.cinema.fapi.rest.controller;

import by.kapitonov.cinema.fapi.model.Cinema;
import by.kapitonov.cinema.fapi.rest.response.ApiResponse;
import by.kapitonov.cinema.fapi.rest.response.PageResponse;
import by.kapitonov.cinema.fapi.service.CinemaService;
import by.kapitonov.cinema.fapi.service.dto.cinema.CreateCinemaDTO;
import by.kapitonov.cinema.fapi.service.dto.cinema.UpdateCinemaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/api/cinemas")
public class CinemaRestController {

    private final CinemaService cinemaService;

    public CinemaRestController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @GetMapping("")
    public ResponseEntity getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        PageResponse<Cinema> cinemas = cinemaService.getAll(page, size);

        return new ResponseEntity(cinemas, HttpStatus.OK);
    }

    @GetMapping("/{owner-id}/all")
    public ResponseEntity getAll(
            @PathVariable(name = "owner-id") Long ownerId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        PageResponse<Cinema> cinemas = cinemaService.getAllOwnerCinemas(ownerId, page, size);

        return new ResponseEntity(cinemas, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Cinema> getOne(@PathVariable(name = "name") String cinemaName) {

        Cinema cinema = cinemaService.getOne(cinemaName);

        return new ResponseEntity(cinema, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody CreateCinemaDTO cinemaDTO) {

        ApiResponse response = cinemaService.create(cinemaDTO);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ApiResponse> update(@RequestBody UpdateCinemaDTO cinemaDTO) {

        ApiResponse response = cinemaService.update(cinemaDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> changeStatus(
            @PathVariable(name = "id") Long cinemaId,
            @PathParam(value = "status") String statusName
    ) {

        ApiResponse response = cinemaService.changeStatus(cinemaId, statusName);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
