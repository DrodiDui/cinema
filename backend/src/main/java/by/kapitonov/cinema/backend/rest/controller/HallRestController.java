package by.kapitonov.cinema.backend.rest.controller;

import by.kapitonov.cinema.backend.model.Hall;
import by.kapitonov.cinema.backend.rest.response.ApiResponse;
import by.kapitonov.cinema.backend.service.HallService;
import by.kapitonov.cinema.backend.service.dto.hall.CreateHallDTO;
import by.kapitonov.cinema.backend.service.dto.hall.HallDTO;
import by.kapitonov.cinema.backend.service.dto.hall.UpdateHallDTO;
import com.sun.corba.se.spi.ior.ObjectKey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
@RequestMapping("/api/halls")
public class HallRestController {

    private final HallService hallService;

    public HallRestController(HallService hallService) {
        this.hallService = hallService;
    }

    @GetMapping("")
    public ResponseEntity getAll(Pageable pageable) {

        Page<HallDTO> hallDTOS = hallService.getAll(pageable);

        return new ResponseEntity(hallDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}/all")
    public ResponseEntity getAll(@PathVariable(name = "id") Long cinemaId, Pageable pageable) {

        Page<HallDTO> hallDTOS = hallService.getAllByCinemaId(cinemaId, pageable);

        return new ResponseEntity(hallDTOS, HttpStatus.OK);
    }

    @GetMapping("{cinema}/{hall}")
    public ResponseEntity<HallDTO> getOne(
            @PathVariable(name = "cinema") String cinemaName,
            @PathVariable(name = "hall") String hallName
    ) {

        HallDTO hallDTO = hallService.getByHallName(hallName, cinemaName);

        return new ResponseEntity<>(hallDTO, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse> create(@RequestBody CreateHallDTO hallDTO) {

        hallService.create(hallDTO);

        return new ResponseEntity<>(new ApiResponse("Hall successfully created"), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<ApiResponse> update(@RequestBody UpdateHallDTO hallDTO) {

        hallService.update(hallDTO);

        return new ResponseEntity<>(new ApiResponse("Hall successfully updated"), HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ApiResponse> updateStatus(
            @PathVariable(name = "id") Long id,
            @RequestParam(value = "status") String statusName) {

        hallService.changStatus(id, statusName);

        return new ResponseEntity<>(new ApiResponse("Hall status successfully updated"), HttpStatus.OK);
    }
}
