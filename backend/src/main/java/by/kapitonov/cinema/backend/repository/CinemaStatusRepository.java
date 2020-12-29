package by.kapitonov.cinema.backend.repository;

import by.kapitonov.cinema.backend.model.CinemaStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CinemaStatusRepository extends JpaRepository<CinemaStatus, Long> {

    Optional<CinemaStatus> findByStatusName(String statusName);

}