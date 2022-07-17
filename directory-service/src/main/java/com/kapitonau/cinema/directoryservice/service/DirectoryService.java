package com.kapitonau.cinema.directoryservice.service;

import com.kapitonau.cinema.directory.dto.DirectoryItemDto;

import java.util.List;

public interface DirectoryService {
    DirectoryItemDto getDirectoryByDirectoryTypeAndCode(String directoryType, String directoryCode);

    DirectoryItemDto getDirectoryByDirectoryId(Long directoryId);

    List<DirectoryItemDto> getAllDirectoryByDirectoryType(String directoryType);
}
