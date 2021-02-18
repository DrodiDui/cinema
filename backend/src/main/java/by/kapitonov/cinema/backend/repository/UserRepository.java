package by.kapitonov.cinema.backend.repository;

import by.kapitonov.cinema.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findByActivationCode(String activationCode);

    Boolean existsByEmail(String email);

}
