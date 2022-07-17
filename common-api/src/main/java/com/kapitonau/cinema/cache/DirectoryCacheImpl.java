package com.kapitonau.cinema.cache;

import com.kapitonau.cinema.cache.feign.client.DirectoryApiClient;
import com.kapitonau.cinema.directory.dto.DirectoryItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class DirectoryCacheImpl implements DirectoryCache<DirectoryItemDto> {

    private final DirectoryApiClient directoryApiClient;

    private Map<String, DirectoryItemDto> directoryTypeCodeCache = new HashMap<>();
    private Map<Long, DirectoryItemDto> directoryIdCache = new HashMap<>();

    @Override
    public DirectoryItemDto directoryByTypeAndCode(String directoryType, String directoryCode) {
        String directoryTypeCode = directoryType + directoryCode;

        if (directoryTypeCodeCache.containsKey(directoryTypeCode)) {
            return directoryTypeCodeCache.get(directoryTypeCode);
        } else {

            DirectoryItemDto directoryItemDto = directoryApiClient.directoryItemByTypeAndCodeGet(directoryType, directoryCode);
            directoryTypeCodeCache.put(directoryTypeCode, directoryItemDto);

            return directoryItemDto;
        }
    }

    @Override
    public DirectoryItemDto directoryById(Long directoryId) {

        if (directoryIdCache.containsKey(directoryId)) {
            return directoryIdCache.get(directoryId);
        } else {

            DirectoryItemDto directoryItemDto = directoryApiClient.directoryItemByDirectoryIdGet(directoryId);
            directoryIdCache.put(directoryId, directoryItemDto);

            return directoryItemDto;
        }
    }
}
