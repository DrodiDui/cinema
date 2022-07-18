package com.kapitonau.cinema.adminservice.service.base;

import com.kapitonau.cinema.admin.dto.organization.OrganizationDto;
import com.kapitonau.cinema.admin.dto.organization.OrganizationPostDto;
import com.kapitonau.cinema.adminservice.model.OrganizationEntity;
import com.kapitonau.cinema.adminservice.repository.OrganizationRepository;
import com.kapitonau.cinema.adminservice.service.OrganizationService;
import com.kapitonau.cinema.cache.DirectoryCache;
import com.kapitonau.cinema.commonspring.dto.EmptyDto;
import com.kapitonau.cinema.commonspring.exception.CommonException;
import com.kapitonau.cinema.directory.dto.DirectoryItemDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Locale;
import java.util.Optional;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Service
@RequiredArgsConstructor
public class BaseOrganizationService implements OrganizationService {

    private final ModelMapper mapper;
    private final MessageSource messageSource;
    private final OrganizationRepository organizationRepository;
    private final DirectoryCache<DirectoryItemDto> directoryCache;

    @Override
    public OrganizationDto getOrgByOrgId(Long orgId) {
        return organizationRepository.findById(orgId)
                .map(organizationEntity -> mapper.map(organizationEntity, OrganizationDto.class))
                .orElseThrow(
                        () -> new CommonException("ADMIN_2", "ADMIN_2", "ADMIN_2", messageSource.getMessage("ADMIN_2", null, getLocale()))
                );
    }

    @Override
    public OrganizationDto createOrg(OrganizationPostDto body) {

        Optional<OrganizationEntity> existOrgEntity = organizationRepository.findByOrgNameAndFullOrgNameAndItn(body.orgName(), body.fullOrgName(), body.itn());
        if (existOrgEntity.isPresent()) {
            throw new CommonException("ADMIN_3", "ADMIN_3", "ADMIN_3", messageSource.getMessage("ADMIN_3", null, getLocale()));
        }

        OrganizationEntity organizationEntity = organizationRepository.save(mapper.map(body, OrganizationEntity.class));

        return mapper.map(organizationEntity, OrganizationDto.class);
    }

    @Override
    public EmptyDto deleteOrganizationByOrgId(Long orgId) {
        organizationRepository.findById(orgId)
                .map(organizationEntity -> {

                    organizationEntity.setCloseDate(ZonedDateTime.now());
                    organizationEntity.setUpdatedDate(ZonedDateTime.now());
                    organizationEntity.setStatusId(directoryCache.directoryByTypeAndCode("ORG_STATUS", "DELETED").directoryItemId());

                    return organizationRepository.save(organizationEntity);
                })
                .orElseThrow(
                        () -> new CommonException("ADMIN_2", "ADMIN_2", "ADMIN_2", messageSource.getMessage("ADMIN_2", null, getLocale()))
                );

        return new EmptyDto();
    }
}
