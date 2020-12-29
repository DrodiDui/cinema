package by.kapitonov.cinema.backend.service.impl;

import by.kapitonov.cinema.backend.exception.ModelNotFoundException;
import by.kapitonov.cinema.backend.model.Cinema;
import by.kapitonov.cinema.backend.model.User;
import by.kapitonov.cinema.backend.repository.CinemaRepository;
import by.kapitonov.cinema.backend.repository.UserRepository;
import by.kapitonov.cinema.backend.service.CinemaService;
import by.kapitonov.cinema.backend.service.CinemaStatusService;
import by.kapitonov.cinema.backend.service.dto.cinema.CinemaDTO;
import by.kapitonov.cinema.backend.service.dto.cinema.CreateCinemaDTO;
import by.kapitonov.cinema.backend.service.dto.cinema.UpdateCinemaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CinemaServiceImpl implements CinemaService {

    private final CinemaRepository cinemaRepository;
    private final CinemaStatusService cinemaStatusService;
    private final UserRepository userRepository;

    public CinemaServiceImpl(
            CinemaRepository cinemaRepository,
            CinemaStatusService cinemaStatusService,
            UserRepository userRepository
    ) {
        this.cinemaRepository = cinemaRepository;
        this.cinemaStatusService = cinemaStatusService;
        this.userRepository = userRepository;
    }


    @Override
    public Page<CinemaDTO> getAll(Pageable pageable) {
        return cinemaRepository.findAll(pageable)
                .map(cinema -> {
                    CinemaDTO cinemaDTO = new CinemaDTO();
                    cinemaDTO.setCinemaName(cinema.getCinemaName());
                    cinemaDTO.setId(cinema.getId());
                    cinemaDTO.setCountry(cinema.getCountry());
                    cinemaDTO.setCity(cinema.getCity());
                    cinemaDTO.setAddress(cinema.getAddress());
                    cinemaDTO.setCreationDate(cinema.getCreationDate());
                    cinemaDTO.setDescription(cinema.getDescription());
                    cinemaDTO.setStatus(cinema.getStatus().getStatusName());
                    return cinemaDTO;
                });
    }

    @Override
    public CinemaDTO getByName(String cinemaName) {
        return cinemaRepository.findByCinemaName(cinemaName)
                .map(cinema -> {
                    CinemaDTO cinemaDTO = new CinemaDTO();
                    cinemaDTO.setCinemaName(cinema.getCinemaName());
                    cinemaDTO.setId(cinema.getId());
                    cinemaDTO.setCountry(cinema.getCountry());
                    cinemaDTO.setCity(cinema.getCity());
                    cinemaDTO.setAddress(cinema.getAddress());
                    cinemaDTO.setCreationDate(cinema.getCreationDate());
                    cinemaDTO.setDescription(cinema.getDescription());
                    cinemaDTO.setStatus(cinema.getStatus().getStatusName());
                    return cinemaDTO;
                })
                .orElseThrow(
                        () -> new ModelNotFoundException("Cinema hasn't been found by cinema name1: " + cinemaName)
                );
    }

    @Override
    public CinemaDTO getById(Long id) {
        return cinemaRepository.findById(id)
                .map(cinema -> {
                    CinemaDTO cinemaDTO = new CinemaDTO();
                    cinemaDTO.setCinemaName(cinema.getCinemaName());
                    cinemaDTO.setId(cinema.getId());
                    cinemaDTO.setCountry(cinema.getCountry());
                    cinemaDTO.setCity(cinema.getCity());
                    cinemaDTO.setAddress(cinema.getAddress());
                    cinemaDTO.setCreationDate(cinema.getCreationDate());
                    cinemaDTO.setDescription(cinema.getDescription());
                    cinemaDTO.setStatus(cinema.getStatus().getStatusName());
                    return cinemaDTO;
                })
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
        cinema.setCreationDate(cinemaDTO.getCreationDate());
        cinema.setDescription(cinemaDTO.getDescription());
        cinema.setStatus(cinemaStatusService.getByName(cinemaDTO.getStatusName()));
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
                    cinema.setCreationDate(cinemaDTO.getCreationDate());
                    cinema.setDescription(cinemaDTO.getDescription());;

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


    private User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(
                        () -> new ModelNotFoundException("User hasn't been fond")
                );
    }
}
