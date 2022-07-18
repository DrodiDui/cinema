package com.kapitonau.cinema.adminservice.repository;

import com.kapitonau.cinema.adminservice.model.CinemaEntity;
import com.kapitonau.cinema.adminservice.repository.custom.CinemaCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CinemaRepository extends JpaRepository<CinemaEntity, Long>, CinemaCustomRepository {
}
