package by.kapitonov.cinema.fapi.rest.controller;

import by.kapitonov.cinema.fapi.model.Hall;
import by.kapitonov.cinema.fapi.rest.response.PageResponse;
import by.kapitonov.cinema.fapi.service.HallService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/halls")
public class HallRestController {

    private final HallService hallService;

    public HallRestController(HallService hallService) {
        this.hallService = hallService;
    }

    @GetMapping("/{id}/all")
    public ResponseEntity getAll(
            @PathVariable(name = "id") Long cinemaId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        PageResponse<Hall> halls = hallService.getAll(cinemaId, page, size);

        return new ResponseEntity<>(halls, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Hall> getOne(@PathVariable(name = "name") String hallName) {

        Hall hall = hallService.getOne(hallName);

        return new ResponseEntity<>(hall, HttpStatus.OK);
    }
}
