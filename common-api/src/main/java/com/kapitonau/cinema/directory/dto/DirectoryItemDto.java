package com.kapitonau.cinema.directory.dto;

import java.util.Map;

public record DirectoryItemDto(

        Long directoryItemId,
        String name,
        String code,
        Map<String, Object> attrs

) {
}
