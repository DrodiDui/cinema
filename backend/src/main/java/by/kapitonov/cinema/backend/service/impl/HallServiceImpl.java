package by.kapitonov.cinema.backend.service.impl;

import by.kapitonov.cinema.backend.exception.ModelNotFoundException;
import by.kapitonov.cinema.backend.model.Cinema;
import by.kapitonov.cinema.backend.model.CinemaStatus;
import by.kapitonov.cinema.backend.model.Hall;
import by.kapitonov.cinema.backend.repository.HallRepository;
import by.kapitonov.cinema.backend.service.CinemaService;
import by.kapitonov.cinema.backend.service.CinemaStatusService;
import by.kapitonov.cinema.backend.service.HallService;
import by.kapitonov.cinema.backend.service.dto.hall.CreateHallDTO;
import by.kapitonov.cinema.backend.service.dto.hall.HallDTO;
import by.kapitonov.cinema.backend.service.dto.hall.UpdateHallDTO;
import by.kapitonov.cinema.backend.service.mapper.HallMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class HallServiceImpl implements HallService {

    private final HallRepository hallRepository;
    private final CinemaStatusService cinemaStatusService;
    private final CinemaService cinemaService;

    public HallServiceImpl(
            HallRepository hallRepository,
            CinemaStatusService cinemaStatusService,
            CinemaService cinemaService
    ) {
        this.hallRepository = hallRepository;
        this.cinemaStatusService = cinemaStatusService;
        this.cinemaService = cinemaService;
    }

    @Override
    public Page<HallDTO> getAll(Pageable pageable) {
        return hallRepository.findAll(pageable)
                .map(HallMapper::toDTO);
    }

    @Override
    public Page<HallDTO> getAllByCinemaId(Long cinemaId, Pageable pageable) {
        return hallRepository.findAllByCinemaId(cinemaId, pageable)
                .map(HallMapper::toDTO);
    }

    @Override
    public Hall getById(Long id) {
        return hallRepository.findById(id)
                .orElseThrow(
                        () -> new ModelNotFoundException("Hall hasn't been found by id: " + id)
                );
    }

    @Override
    public HallDTO getByHallName(String hallName, String cinemaName) {
        return hallRepository.findByHallNameAndCinemaCinemaName(hallName, cinemaName)
                .map(HallMapper::toDTO)
                .orElseThrow(
                        () -> new ModelNotFoundException("Hall hasn't been found by name: " + hallName)
                );
    }

    @Override
    public Integer getAllNumberOfSeats(Long id) {
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
        hall.setStatus(getCinemaStatus(hallDTO.getStatus()));
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
                    hall.setStatus(getCinemaStatus(hallDTO.getStatus()));

                    return hallRepository.save(hall);
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
        return cinemaService.getById(cinemaId);
    }

    private CinemaStatus getCinemaStatus(String statusName) {
        return cinemaStatusService.getByName(statusName);
    }

}
