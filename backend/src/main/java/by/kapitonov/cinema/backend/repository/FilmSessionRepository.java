package by.kapitonov.cinema.backend.repository;

import by.kapitonov.cinema.backend.model.FilmSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmSessionRepository extends JpaRepository<FilmSession, Long> {

    Page<FilmSession> findAllByStatusEquals(String filmSessionStatus, Pageable pageable);

}