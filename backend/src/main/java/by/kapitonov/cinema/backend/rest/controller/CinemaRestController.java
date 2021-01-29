package by.kapitonov.cinema.backend.rest.controller;

import by.kapitonov.cinema.backend.rest.response.ApiResponse;
import by.kapitonov.cinema.backend.service.CinemaService;
import by.kapitonov.cinema.backend.service.dto.cinema.CinemaDTO;
import by.kapitonov.cinema.backend.service.dto.cinema.CreateCinemaDTO;
import by.kapitonov.cinema.backend.service.dto.cinema.UpdateCinemaDTO;
import by.kapitonov.cinema.backend.service.mapper.PageableMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cinemas")
public class CinemaRestController {

    private final CinemaService cinemaService;

    public CinemaRestController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @GetMapping("")
    public ResponseEntity getAll(@RequestParam Map<String, String> pageableParams) {

        Pageable pageable = PageableMapper.mapToPageable(pageableParams);

        Page<CinemaDTO> cinemaDTOS = cinemaService.getAll(pageable);

        return new ResponseEntity(cinemaDTOS, HttpStatus.OK);
    }

    @GetMapping("/all/{owner-id}")
    public ResponseEntity getAll(
            @PathVariable(name = "owner-id") Long ownerId,
            @RequestParam Map<String, String> pageableParams
    ) {

        Pageable pageable = PageableMapper.mapToPageable(pageableParams);

        Page<CinemaDTO> cinemaDTOS = cinemaService.getAllByOwnerId(ownerId, pageable);

        return new ResponseEntity(cinemaDTOS, HttpStatus.OK);
    }

    @GetMapping("{name}")
    public ResponseEntity<CinemaDTO> getOne(@PathVariable(name = "name") String cinemaName) {

        CinemaDTO cinemaDTO = cinemaService.getByName(cinemaName);

        return new ResponseEntity<>(cinemaDTO, HttpStatus.OK);
    }

    @GetMapping("/manager/{id}")
    public ResponseEntity<CinemaDTO> getOne(@PathVariable(name = "id") Long managerId) {

        CinemaDTO cinemaDTO = cinemaService.getByManagerId(managerId);

        return new ResponseEntity<>(cinemaDTO, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse> create(@RequestBody CreateCinemaDTO cinemaDTO) {

        cinemaService.create(cinemaDTO);

        return new ResponseEntity<>(new ApiResponse("Cinema successfully created"), HttpStatus.OK);
    }

    @PatchMapping("")
    public ResponseEntity<ApiResponse> update(@RequestBody UpdateCinemaDTO cinemaDTO) {

        cinemaService.update(cinemaDTO);

        return new ResponseEntity<>(new ApiResponse("Cinema successfully updated"), HttpStatus.OK);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse> updateStatus(
            @PathVariable(name = "id") Long id,
            @RequestParam(value = "status") String statusName
    ) {

        cinemaService.changeStatus(id, statusName);

        return new ResponseEntity<>(new ApiResponse("Cinema status successfully updated"), HttpStatus.OK);
    }

}
