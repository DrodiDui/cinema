package com.kapitonau.cinema.adminservice.repository;

import com.kapitonau.cinema.adminservice.model.OrganizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<OrganizationEntity, Long> {


    Optional<OrganizationEntity> findByOrgNameAndFullOrgNameAndItn(String orgName, String fullOrgName, Long itn);

}
