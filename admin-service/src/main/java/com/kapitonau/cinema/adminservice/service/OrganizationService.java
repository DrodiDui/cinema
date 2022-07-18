package com.kapitonau.cinema.adminservice.service;

import com.kapitonau.cinema.admin.dto.organization.OrganizationDto;
import com.kapitonau.cinema.admin.dto.organization.OrganizationPostDto;
import com.kapitonau.cinema.commonspring.dto.EmptyDto;

public interface OrganizationService {

    OrganizationDto getOrgByOrgId(Long orgId);

    OrganizationDto createOrg(OrganizationPostDto body);

    EmptyDto deleteOrganizationByOrgId(Long orgId);
}
