package by.kapitonov.cinema.backend.service.impl;

import by.kapitonov.cinema.backend.exception.ModelNotFoundException;
import by.kapitonov.cinema.backend.model.Cinema;
import by.kapitonov.cinema.backend.model.Hall;
import by.kapitonov.cinema.backend.repository.CinemaRepository;
import by.kapitonov.cinema.backend.repository.HallRepository;
import by.kapitonov.cinema.backend.service.CinemaStatusService;
import by.kapitonov.cinema.backend.service.HallService;
import by.kapitonov.cinema.backend.service.dto.hall.CreateHallDTO;
import by.kapitonov.cinema.backend.service.dto.hall.HallDTO;
import by.kapitonov.cinema.backend.service.dto.hall.UpdateHallDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class HallServiceImpl implements HallService {

    private final HallRepository hallRepository;
    private final CinemaRepository cinemaRepository;
    private final CinemaStatusService cinemaStatusService;

    public HallServiceImpl(
            HallRepository hallRepository,
            CinemaRepository cinemaRepository,
            CinemaStatusService cinemaStatusService
    ) {
        this.hallRepository = hallRepository;
        this.cinemaRepository = cinemaRepository;
        this.cinemaStatusService = cinemaStatusService;
    }

    @Override
    public Page<HallDTO> getAll(Pageable pageable) {
        return hallRepository.findAll(pageable)
                .map(hall -> {
                    HallDTO hallDTO = new HallDTO();
                    hallDTO.setId(hall.getId());
                    hallDTO.setHallName(hall.getHallName());
                    hallDTO.setCinemaName(hall.getCinema().getCinemaName());
                    hallDTO.setFloor(hall.getFloor());
                    hallDTO.setRowsNumbers(hall.getRowsNumbers());
                    hallDTO.setNumberSeatsPerRow(hall.getNumberSeatsPerRow());
                    hallDTO.setStatus(hall.getStatus().getStatusName());
                    return hallDTO;
                });
    }

    @Override
    public HallDTO getById(Long id) {
        return hallRepository.findById(id)
                .map(hall -> {
                    HallDTO hallDTO = new HallDTO();
                    hallDTO.setId(hall.getId());
                    hallDTO.setHallName(hall.getHallName());
                    hallDTO.setCinemaName(hall.getCinema().getCinemaName());
                    hallDTO.setFloor(hall.getFloor());
                    hallDTO.setRowsNumbers(hall.getRowsNumbers());
                    hallDTO.setNumberSeatsPerRow(hall.getNumberSeatsPerRow());
                    hallDTO.setStatus(hall.getStatus().getStatusName());
                    return hallDTO;
                })
                .orElseThrow(
                        () -> new ModelNotFoundException("Hall hasn't been found by id: " + id)
                );
    }

    @Override
    public Integer getNumberOfSeats(Long id) {
        return hallRepository.findById(id)
                .map(hall -> hall.getRowsNumbers() * hall.getNumberSeatsPerRow())
                .orElseThrow(
                        () -> new ModelNotFoundException("Hall hasn't been found by id: " + id)
                );
    }

    @Override
    public Hall create(CreateHallDTO hallDTO) {
        Hall hall = new Hall();
        hall.setHallName(hallDTO.getHallName());
        hall.setFloor(hallDTO.getFloor());
        hall.setRowsNumbers(hallDTO.getRowsNumbers());
        hall.setNumberSeatsPerRow(hallDTO.getNumberSeatsPerRow());
        hall.setStatus(cinemaStatusService.getByName(hallDTO.getStatus()));
        hall.setCinema(getCinema(hallDTO.getCinemaId()));
        return hallRepository.save(hall);
    }


    @Override
    public Hall update(UpdateHallDTO hallDTO) {
        return hallRepository.findById(hallDTO.getId())
                .map(hall -> {
                    hall.setHallName(hallDTO.getHallName());
                    hall.setFloor(hallDTO.getFloor());
                    hall.setRowsNumbers(hallDTO.getRowsNumbers());
                    hall.setNumberSeatsPerRow(hallDTO.getNumberSeatsPerRow());
                    return hall;
                })
                .orElseThrow(
                        () -> new ModelNotFoundException("Hall hasn't been found by id: " + hallDTO.getId())
                );
    }

    @Override
    public Hall changStatus(Long id, String statusName) {
        return hallRepository.findById(id)
                .map(hall -> {
                    hall.setStatus(cinemaStatusService.getByName(statusName));
                    return hallRepository.save(hall);
                })
                .orElseThrow(
                        () -> new ModelNotFoundException("Hall hasn't been found by id: " + id)
                );
    }

    private Cinema getCinema(Long cinemaId) {
        return cinemaRepository.findById(cinemaId)
                .orElseThrow(
                        () -> new ModelNotFoundException("Cinema hasn't been found by id: " + cinemaId)
                );
    }
}
