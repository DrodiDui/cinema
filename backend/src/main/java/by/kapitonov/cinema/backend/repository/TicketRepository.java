package by.kapitonov.cinema.backend.repository;

import by.kapitonov.cinema.backend.model.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Page<Ticket> findByUserId(Long id, Pageable pageable);
    Page<Ticket> findAllByReservedFalseAndFilmSessionId(Long sessionId, Pageable pageable);

    List<Ticket> findAllByReservedTrueAndUserId(Long userId);

}