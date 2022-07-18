package com.kapitonau.cinema.admin.dto.organization;

import com.kapitonau.cinema.directory.dto.DirectoryItemDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrganizationDto {

    private Long orgId;

    private String orgName;

    private String fullOrgName;

    private DirectoryItemDto orgStatus;

    private Long itn;

    private String address;

}
