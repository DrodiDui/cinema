package com.kapitonau.cinema.directoryservice.controller;

import com.kapitonau.cinema.directory.api.DirectoryApi;
import com.kapitonau.cinema.directory.dto.DirectoryItemDto;
import com.kapitonau.cinema.directoryservice.service.DirectoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DirectoryController implements DirectoryApi {

    private final DirectoryService directoryService;

    @Override
    public DirectoryItemDto directoryItemByTypeAndCodeGet(String directoryType, String directoryCode) {
        return directoryService.getDirectoryByDirectoryTypeAndCode(directoryType, directoryCode);
    }

    @Override
    public DirectoryItemDto directoryItemByDirectoryIdGet(Long directoryId) {
        return directoryService.getDirectoryByDirectoryId(directoryId);
    }

    @Override
    public List<DirectoryItemDto> allDirectoryItemByDirectoryCode(String directoryType) {
        return directoryService.getAllDirectoryByDirectoryType(directoryType);
    }
}
