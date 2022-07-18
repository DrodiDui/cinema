package com.kapitonau.cinema.adminservice.mapper;

import com.kapitonau.cinema.admin.dto.organization.OrganizationDto;
import com.kapitonau.cinema.admin.dto.organization.OrganizationPostDto;
import com.kapitonau.cinema.adminservice.model.OrganizationEntity;
import com.kapitonau.cinema.cache.DirectoryCache;
import com.kapitonau.cinema.directory.dto.DirectoryItemDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.ZonedDateTime;

@Component
@RequiredArgsConstructor
public class OrganizationMapper {

    private final ModelMapper mapper;
    private final DirectoryCache<DirectoryItemDto> directoryCache;

    @PostConstruct
    public void init() {

        Converter<Long, DirectoryItemDto> statusIdToDirectoryItemDto = new AbstractConverter<Long, DirectoryItemDto>() {
            @Override
            protected DirectoryItemDto convert(Long source) {
                if (source != null) {
                    return directoryCache.directoryById(source);
                }
                return null;
            }
        };

        Converter<String, Long> orgStatusCodeToStatusId = new AbstractConverter<String, Long>() {
            @Override
            protected Long convert(String source) {
                if (source != null) {
                    return directoryCache.directoryByTypeAndCode("ORG_STATUS", source).directoryItemId();
                }
                return null;
            }
        };

        mapper
                .typeMap(OrganizationEntity.class, OrganizationDto.class)
                .addMappings(mapper -> {
                    mapper.map(src -> src.getOrgId(), OrganizationDto::setOrgId);
                    mapper.map(src -> src.getOrgName(), OrganizationDto::setOrgName);
                    mapper.map(src -> src.getFullOrgName(), OrganizationDto::setFullOrgName);
                    mapper.map(src -> src.getItn(), OrganizationDto::setItn);
                    mapper.using(statusIdToDirectoryItemDto).map(src -> src.getStatusId(), OrganizationDto::setOrgId);
                    mapper.map(src -> src.getAddress(), OrganizationDto::setAddress);
                });

        mapper
                .typeMap(OrganizationPostDto.class, OrganizationEntity.class)
                .addMappings(mapper -> {
                    mapper.map(src -> src.orgName(), OrganizationEntity::setOrgName);
                    mapper.map(src -> src.fullOrgName(), OrganizationEntity::setFullOrgName);
                    mapper.map(src -> src.itn(), OrganizationEntity::setItn);
                    mapper.map(src -> src.address(), OrganizationEntity::setAddress);
                    mapper.map(src -> src.illegalAddress(), OrganizationEntity::setIllegalAddress);
                    mapper.map(src -> src.phoneNumber(), OrganizationEntity::setPhoneNumber);
                    mapper.using(orgStatusCodeToStatusId).map(src -> "ADDED", OrganizationEntity::setStatusId);
                    mapper.map(src -> ZonedDateTime.now(), OrganizationEntity::setCreatedDate);
                });

    }

}
