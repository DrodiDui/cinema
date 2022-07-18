package com.kapitonau.cinema.admin.api;

import com.kapitonau.cinema.admin.dto.organization.OrganizationPostDto;
import com.kapitonau.cinema.admin.dto.organization.OrganizationDto;
import com.kapitonau.cinema.commonspring.dto.EmptyDto;
import org.springframework.web.bind.annotation.*;

public interface OrganizationApi {

    @GetMapping("/organizations/{orgId}")
    OrganizationDto getOrgByOrgId(@PathVariable(name = "orgId") Long orgId);

    @PostMapping("/organizations")
    OrganizationDto organizationPost(@RequestBody OrganizationPostDto body);

    @DeleteMapping("/organizations/{orgId}")
    EmptyDto organizationByOrgIdDelete(@PathVariable(name = "orgId") Long orgId);

}
