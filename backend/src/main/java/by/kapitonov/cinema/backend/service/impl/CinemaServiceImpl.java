package by.kapitonov.cinema.backend.service.impl;

import by.kapitonov.cinema.backend.exception.ModelNotFoundException;
import by.kapitonov.cinema.backend.model.Cinema;
import by.kapitonov.cinema.backend.model.CinemaStatus;
import by.kapitonov.cinema.backend.model.User;
import by.kapitonov.cinema.backend.repository.CinemaRepository;
import by.kapitonov.cinema.backend.service.CinemaService;
import by.kapitonov.cinema.backend.service.CinemaStatusService;
import by.kapitonov.cinema.backend.service.UserService;
import by.kapitonov.cinema.backend.service.dto.cinema.CinemaDTO;
import by.kapitonov.cinema.backend.service.dto.cinema.CreateCinemaDTO;
import by.kapitonov.cinema.backend.service.dto.cinema.UpdateCinemaDTO;
import by.kapitonov.cinema.backend.service.mapper.CinemaMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CinemaServiceImpl implements CinemaService {

    private final CinemaRepository cinemaRepository;
    private final CinemaStatusService cinemaStatusService;
    private final UserService userService;

    public CinemaServiceImpl(
            CinemaRepository cinemaRepository,
            CinemaStatusService cinemaStatusService,
            UserService userService
    ) {
        this.cinemaRepository = cinemaRepository;
        this.cinemaStatusService = cinemaStatusService;
        this.userService = userService;
    }


    @Override
    public Page<CinemaDTO> getAll(Pageable pageable) {
        return cinemaRepository.findAll(pageable)
                .map(CinemaMapper::toDTO);
    }

    @Override
    public Page<CinemaDTO> getAllByOwnerId(Long ownerId, Pageable pageable) {
        return cinemaRepository.findAllByOwnerId(ownerId, pageable)
                .map(CinemaMapper::toDTO);
    }

    @Override
    public CinemaDTO getByName(String cinemaName) {
        return cinemaRepository.findByCinemaName(cinemaName)
                .map(CinemaMapper::toDTO)
                .orElseThrow(
                        () -> new ModelNotFoundException("Cinema hasn't been found by cinema name: " + cinemaName)
                );
    }

    @Override
    public Cinema getById(Long id) {
        return cinemaRepository.findById(id)
                .orElseThrow(
                        () -> new ModelNotFoundException("Cinema hasn't been found by id: " + id)
                );
    }

    @Override
    public Cinema create(CreateCinemaDTO cinemaDTO) {
        Cinema cinema = new Cinema();
        cinema.setCinemaName(cinemaDTO.getCinemaName());
        cinema.setCountry(cinemaDTO.getCountry());
        cinema.setCity(cinemaDTO.getCity());
        cinema.setAddress(cinemaDTO.getAddress());
        cinema.setCreationDate(new Date());
        cinema.setDescription(cinemaDTO.getDescription());
        cinema.setStatus(getCinemaStatus(cinemaDTO.getStatusName()));
        cinema.setOwner(getUser(cinemaDTO.getOwnerId()));

        return cinemaRepository.save(cinema);
    }

    @Override
    public Cinema update(UpdateCinemaDTO cinemaDTO) {
        return cinemaRepository.findById(cinemaDTO.getId())
                .map(cinema -> {
                    cinema.setCinemaName(cinemaDTO.getCinemaName());
                    cinema.setCountry(cinemaDTO.getCountry());
                    cinema.setCity(cinemaDTO.getCity());
                    cinema.setAddress(cinemaDTO.getAddress());
                    cinema.setDescription(cinemaDTO.getDescription());;
                    cinema.setStatus(getCinemaStatus(cinemaDTO.getStatus()));

                    return cinemaRepository.save(cinema);
                })
                .orElseThrow(
                        () -> new ModelNotFoundException("Cinema hasn't been found by id: " + cinemaDTO.getId())
                );

    }

    @Override
    public Cinema changeStatus(Long id, String statusName) {
        return cinemaRepository.findById(id)
                .map(cinema -> {
                    cinema.setStatus(cinemaStatusService.getByName(statusName));
                    return cinemaRepository.save(cinema);
                })
                .orElseThrow(
                        () -> new ModelNotFoundException("User hasn't been found by id: " + id)
                );
    }

    @Override
    public CinemaDTO getByManagerId(Long managerId) {
        Cinema cinema = getUser(managerId).getCinema();
        return CinemaMapper.toDTO(cinema);
    }

    private User getUser(Long id) {
        return userService.getById(id);
    }

    private CinemaStatus getCinemaStatus(String statusName) {
        return cinemaStatusService.getByName(statusName);
    }

}
