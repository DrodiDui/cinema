package by.kapitonov.cinema.backend.repository;

import by.kapitonov.cinema.backend.model.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

    Page<Film> findByOwnerId(Long id, Pageable pageable);

}