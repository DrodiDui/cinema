package com.kapitonau.cinema.admin.dto.cinema;

import com.kapitonau.cinema.directory.dto.DirectoryItemDto;
import lombok.*;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CinemaDto {

    private Long cinemaId;
    private String cinemaName;
    private String country;
    private String city;
    private String address;
    private String description;
    private DirectoryItemDto cinemaStatus;

}
