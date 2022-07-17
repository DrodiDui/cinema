package com.kapitonau.cinema.directoryservice.model.mapper;

import com.kapitonau.cinema.directory.dto.DirectoryItemDto;
import com.kapitonau.cinema.directoryservice.model.DirectoryEntity;
import org.springframework.stereotype.Component;

@Component
public class DirectoryMapper {

    public DirectoryItemDto directoryToDirectoryItemDto(DirectoryEntity directoryEntity) {
        return new DirectoryItemDto(
                directoryEntity.getDirectoryId(),
                directoryEntity.getName(),
                directoryEntity.getCode(),
                directoryEntity.getDirectoryAttrs()
        );
    }


}
