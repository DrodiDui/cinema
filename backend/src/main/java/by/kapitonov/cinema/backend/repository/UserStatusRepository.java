package by.kapitonov.cinema.backend.repository;

import by.kapitonov.cinema.backend.model.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserStatusRepository extends JpaRepository<UserStatus, Long> {

    Optional<UserStatus> findByStatusName(String statusName);

}