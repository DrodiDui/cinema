package by.kapitonov.cinema.backend.service.impl;

import by.kapitonov.cinema.backend.exception.ModelNotFoundException;
import by.kapitonov.cinema.backend.repository.UserStatusRepository;
import by.kapitonov.cinema.backend.service.UserStatusService;
import by.kapitonov.cinema.backend.model.UserStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserStatusServiceImpl implements UserStatusService {

    private final UserStatusRepository statusRepository;

    public UserStatusServiceImpl(UserStatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public List<UserStatus> getAll() {
        return statusRepository.findAll();
    }

    @Override
    public List<String> getAllStatusNames() {
        return statusRepository.findAll()
                .stream()
                .map(UserStatus::getStatusName)
                .collect(Collectors.toList());
    }

    @Override
    public UserStatus getByName(String statusName) {
        return statusRepository.findByStatusName(statusName)
                .orElseThrow(
                        () -> new ModelNotFoundException("User status name hasn't been found")
                );
    }

    @Override
    public UserStatus create(String statusName) {
        return statusRepository.save(new UserStatus(statusName));
    }
}
