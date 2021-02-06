package by.kapitonov.cinema.backend.service.impl;

import by.kapitonov.cinema.backend.exception.ModelNotFoundException;
import by.kapitonov.cinema.backend.repository.CinemaStatusRepository;
import by.kapitonov.cinema.backend.service.CinemaStatusService;
import by.kapitonov.cinema.backend.model.CinemaStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CinemaStatusServiceImpl implements CinemaStatusService {

    private final CinemaStatusRepository statusRepository;

    public CinemaStatusServiceImpl(CinemaStatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public List<String> getAllStatusNames() {
        return statusRepository.findAll()
                .stream()
                .map(CinemaStatus::getStatusName)
                .collect(Collectors.toList());
    }

    @Override
    public CinemaStatus getByName(String statusName) {
        return statusRepository.findByStatusName(statusName)
                .orElseThrow(
                        () -> new ModelNotFoundException("Cinema status hasn't been found by name: " + statusName)
                );
    }

    @Override
    public CinemaStatus create(String statusName) {
        return statusRepository.save(new CinemaStatus(statusName));
    }
}
