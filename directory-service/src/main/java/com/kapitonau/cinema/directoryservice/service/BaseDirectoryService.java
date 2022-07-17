package com.kapitonau.cinema.directoryservice.service;

import com.kapitonau.cinema.commonspring.exception.CommonException;
import com.kapitonau.cinema.directory.dto.DirectoryItemDto;
import com.kapitonau.cinema.directoryservice.model.mapper.DirectoryMapper;
import com.kapitonau.cinema.directoryservice.repository.DirectoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Service
@RequiredArgsConstructor
public class BaseDirectoryService implements DirectoryService {

    private final DirectoryRepository directoryRepository;
    private final DirectoryMapper directoryMapper;
    private final MessageSource messageSource;

    @Override
    public DirectoryItemDto getDirectoryByDirectoryTypeAndCode(String directoryType, String directoryCode) {
        return directoryRepository.findByDirectoryTypeAndDirectoryCode(directoryType, directoryCode)
                .map(directoryMapper::directoryToDirectoryItemDto)
                .orElseThrow(
                        () -> new CommonException("DIRECTORY_1", "DIRECTORY_1", "DIRECTORY_1", messageSource.getMessage("DIRECTORY_1", null, getLocale()))
                );
    }

    @Override
    public DirectoryItemDto getDirectoryByDirectoryId(Long directoryId) {
        return directoryRepository.findById(directoryId)
                .map(directoryMapper::directoryToDirectoryItemDto)
                .orElseThrow(
                        () -> new CommonException("DIRECTORY_1", "DIRECTORY_1", "DIRECTORY_1", messageSource.getMessage("DIRECTORY_1", null, getLocale()))
                );
    }

    @Override
    public List<DirectoryItemDto> getAllDirectoryByDirectoryType(String directoryType) {
        return directoryRepository.findAllByDirectoryType(directoryType)
                .stream()
                .map(directoryMapper::directoryToDirectoryItemDto)
                .collect(Collectors.toList());
    }
}
