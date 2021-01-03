package by.kapitonov.cinema.backend.repository;

import by.kapitonov.cinema.backend.model.Cinema;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long> {

    Page<Cinema> findAllByOwnerId(Long ownerId, Pageable pageable);

    Optional<Cinema> findByCinemaName(String cinemaName);

}