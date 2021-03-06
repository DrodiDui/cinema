package by.kapitonov.cinema.backend.repository;

import by.kapitonov.cinema.backend.model.FilmSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;

@Repository
public interface FilmSessionRepository extends JpaRepository<FilmSession, Long> {

   Page<FilmSession> findAllByStatusStatusNameAndHallId(String statusName, Long hallId, Pageable pageable);
   Page<FilmSession> findAllByHallId(Long hallId, Pageable pageable);

   Optional<FilmSession> findFilmSessionByShowTimeAndHallId(Instant showTime, Long hallId);

}