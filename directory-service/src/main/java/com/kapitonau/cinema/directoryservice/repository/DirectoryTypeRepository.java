package com.kapitonau.cinema.directoryservice.repository;

import com.kapitonau.cinema.directoryservice.model.DirectoryTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectoryTypeRepository extends JpaRepository<DirectoryTypeEntity, Long> {
}
