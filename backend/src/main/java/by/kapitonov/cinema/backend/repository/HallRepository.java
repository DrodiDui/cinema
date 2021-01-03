package by.kapitonov.cinema.backend.repository;

import by.kapitonov.cinema.backend.model.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HallRepository extends JpaRepository<Hall, Long> {

    Optional<Hall> findByHallName(String hallName);

}