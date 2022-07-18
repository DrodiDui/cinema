package com.kapitonau.cinema.adminservice.service.base;

import com.kapitonau.cinema.admin.dto.cinema.CinemaDto;
import com.kapitonau.cinema.admin.dto.cinema.CinemaPageDto;
import com.kapitonau.cinema.admin.dto.cinema.CinemaPostDto;
import com.kapitonau.cinema.adminservice.model.CinemaEntity;
import com.kapitonau.cinema.adminservice.repository.CinemaRepository;
import com.kapitonau.cinema.adminservice.service.CinemaService;
import com.kapitonau.cinema.commonspring.dto.EmptyDto;
import com.kapitonau.cinema.commonspring.exception.CommonException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Service
@RequiredArgsConstructor
public class BaseCinemaService implements CinemaService {

    private CinemaRepository cinemaRepository;
    private ModelMapper mapper;
    private final MessageSource messageSource;


    @Override
    public CinemaPageDto getAllCinema(Long offset, Long limit) {

        List<CinemaDto> cinemaDtoList = cinemaRepository.findAllByFilters(offset, limit)
                .stream()
                .map(cinemaEntity -> mapper.map(cinemaEntity, CinemaDto.class))
                .toList();

        Long allCinemaCount = cinemaRepository.cinemaCountByFilters();

        return new CinemaPageDto(allCinemaCount, cinemaDtoList, ZonedDateTime.now());
    }

    @Override
    public CinemaDto getById(Long cinemaId) {
        return cinemaRepository.findById(cinemaId)
                .map(cinemaEntity -> mapper.map(cinemaEntity, CinemaDto.class))
                .orElseThrow(
                        () -> new CommonException("ADMIN_1", "ADMIN_1", "ADMIN_1", messageSource.getMessage("ADMIN_1", null, getLocale()))
                );
    }

    @Override
    public CinemaDto create(CinemaPostDto body) {

        //TODO: add validation for org binding

        //TODO: add validation by cinema name in once organization

        CinemaEntity cinemaEntity = mapper.map(body, CinemaEntity.class);

        return mapper.map(cinemaRepository.save(cinemaEntity), CinemaDto.class);
    }

    @Override
    public CinemaDto change(CinemaEntity cinemaEntity) {

        //TODO: add edit cinema impl

        return null;
    }

    @Override
    public EmptyDto delete(Long cinemaId) {

        //TODO: add org binding validation

        cinemaRepository.findById(cinemaId)
                .map(cinemaEntity -> {
                    ZonedDateTime dateOfRemoved = ZonedDateTime.now();
                    cinemaEntity.setUpdatedDate(dateOfRemoved);
                    cinemaEntity.setUpdatedBy("username");
                    cinemaEntity.setUpdatedByRole("ADMIN");
                    cinemaEntity.setCloseDate(dateOfRemoved);
                    return cinemaEntity;
                })
                .orElseThrow(
                        () -> new CommonException("ADMIN_1", "ADMIN_1", "ADMIN_1", "Cinema hasn't been found")
                );

        return new EmptyDto();
    }
}
