package com.kapitonau.cinema.admin.dto.organization;

public record OrganizationPostDto(

        String orgName,
        String fullOrgName,
        Long itn,
        String address,
        String illegalAddress,
        String phoneNumber

) {
}
