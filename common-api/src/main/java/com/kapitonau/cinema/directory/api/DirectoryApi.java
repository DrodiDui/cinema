package com.kapitonau.cinema.directory.api;

import com.kapitonau.cinema.directory.dto.DirectoryItemDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface DirectoryApi {

    @GetMapping("/directory")
    DirectoryItemDto directoryItemByTypeAndCodeGet(@RequestParam(name = "directoryType", required = true) String directoryType, @RequestParam(name = "directoryCode", required = true) String directoryCode);

    @GetMapping("/directory/{directoryId}")
    DirectoryItemDto directoryItemByDirectoryIdGet(@PathVariable(name = "directoryId") Long directoryId);

    @GetMapping("/directory/{directoryType}/all")
    List<DirectoryItemDto> allDirectoryItemByDirectoryCode(@PathVariable(name = "directoryType") String directoryType);

}
