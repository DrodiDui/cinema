package com.kapitonau.cinema.adminservice.mapper;

import com.kapitonau.cinema.admin.dto.cinema.CinemaDto;
import com.kapitonau.cinema.admin.dto.cinema.CinemaPostDto;
import com.kapitonau.cinema.adminservice.model.CinemaEntity;
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
public class CinemaMapper {

    private final ModelMapper mapper;
    private final DirectoryCache<DirectoryItemDto> directoryCache;

    @PostConstruct
    public void init() {

        Converter<String, Long> cinemaStatusCodeToId = new AbstractConverter<String, Long>() {
            @Override
            protected Long convert(String source) {
                if (source != null) {
                    return directoryCache.directoryByTypeAndCode("CINEMA_STATUS", source).directoryItemId();
                }
                return null;
            }
        };

        Converter<Long, DirectoryItemDto> cinemaStatusIdToDirectoryItemDto = new AbstractConverter<Long, DirectoryItemDto>() {
            @Override
            protected DirectoryItemDto convert(Long source) {
                if (source != null) {
                    return directoryCache.directoryById(source);
                }
                return null;
            }
        };

        mapper
                .typeMap(CinemaPostDto.class, CinemaEntity.class)
                .addMappings(mapper -> {
                    mapper.map(src -> src.cinemaName(), CinemaEntity::setCinemaName);
                    mapper.map(src -> src.country(), CinemaEntity::setCountry);
                    mapper.map(src -> src.city(), CinemaEntity::setCity);
                    mapper.map(src -> src.address(), CinemaEntity::setAddress);
                    mapper.map(src -> src.description(), CinemaEntity::setDescription);
                    mapper.using(cinemaStatusCodeToId).map(src -> src.cinemaStatus(), CinemaEntity::setCinemaId);
                    mapper.map(src -> ZonedDateTime.now(), CinemaEntity::setCreatedDate);

                });

        mapper
                .typeMap(CinemaEntity.class, CinemaDto.class)
                .addMappings(mapper -> {
                    mapper.map(src -> src.getCinemaId(), CinemaDto::setCinemaId);
                    mapper.map(src -> src.getCinemaName(), CinemaDto::setCinemaName);
                    mapper.map(src -> src.getCountry(), CinemaDto::setCountry);
                    mapper.map(src -> src.getCity(), CinemaDto::setCity);
                    mapper.map(src -> src.getAddress(), CinemaDto::setAddress);
                    mapper.map(src -> src.getDescription(), CinemaDto::setDescription);
                    mapper.using(cinemaStatusIdToDirectoryItemDto).map(src -> src.getStatusId(), CinemaDto::setCinemaStatus);
                });

    }


}
