package by.kapitonov.cinema.backend.rest.controller;

import by.kapitonov.cinema.backend.rest.response.ApiResponse;
import by.kapitonov.cinema.backend.service.CinemaStatusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cinema-statuses")
public class CinemaStatusRestController {

    private final CinemaStatusService cinemaStatusService;

    public CinemaStatusRestController(CinemaStatusService cinemaStatusService) {
        this.cinemaStatusService = cinemaStatusService;
    }

    @GetMapping("")
    public ResponseEntity getAll() {

        List<String> statuses = cinemaStatusService.getAllStatusNames();

        return new ResponseEntity(statuses, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse> create(@RequestParam(value = "status") String statusName) {

        cinemaStatusService.create(statusName);

        return new ResponseEntity<>(new ApiResponse("Cinema status successfully created"), HttpStatus.OK);
    }
}
