package com.kapitonau.cinema.adminservice.controller;

import com.kapitonau.cinema.admin.api.OrganizationApi;
import com.kapitonau.cinema.admin.dto.organization.OrganizationDto;
import com.kapitonau.cinema.admin.dto.organization.OrganizationPostDto;
import com.kapitonau.cinema.adminservice.service.OrganizationService;
import com.kapitonau.cinema.commonspring.dto.EmptyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrganizationRestController implements OrganizationApi {

    private final OrganizationService organizationService;

    @Override
    public OrganizationDto getOrgByOrgId(Long orgId) {
        return organizationService.getOrgByOrgId(orgId);
    }

    @Override
    public OrganizationDto organizationPost(OrganizationPostDto body) {
        return organizationService.createOrg(body);
    }

    @Override
    public EmptyDto organizationByOrgIdDelete(Long orgId) {
        return organizationService.deleteOrganizationByOrgId(orgId);
    }
}
