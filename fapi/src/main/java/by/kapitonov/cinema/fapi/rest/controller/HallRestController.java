package by.kapitonov.cinema.fapi.rest.controller;

import by.kapitonov.cinema.fapi.model.Hall;
import by.kapitonov.cinema.fapi.rest.response.ApiResponse;
import by.kapitonov.cinema.fapi.rest.response.PageResponse;
import by.kapitonov.cinema.fapi.service.HallService;
import by.kapitonov.cinema.fapi.service.dto.CreateHallDTO;
import by.kapitonov.cinema.fapi.service.dto.UpdateHallDTO;
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

    @GetMapping("/{cinema}/{hall}")
    public ResponseEntity<Hall> getOne(
            @PathVariable(name = "cinema") String cinemaName,
            @PathVariable(name = "hall") String hallName
    ) {

        Hall hall = hallService.getOne(cinemaName, hallName);

        return new ResponseEntity<>(hall, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> creat(@RequestBody CreateHallDTO hallDTO) {

        ApiResponse response = hallService.create(hallDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ApiResponse> update(@RequestBody UpdateHallDTO hallDTO) {

        ApiResponse response = hallService.update(hallDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
