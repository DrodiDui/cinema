package com.kapitonau.cinema.directoryservice.repository;

import com.kapitonau.cinema.directoryservice.model.DirectoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DirectoryRepository extends JpaRepository<DirectoryEntity, Long> {


    Optional<DirectoryEntity> findByDirectoryTypeAndDirectoryCode(String directoryType, String directoryCode);

    List<DirectoryEntity> findAllByDirectoryType(String directoryType);


}
