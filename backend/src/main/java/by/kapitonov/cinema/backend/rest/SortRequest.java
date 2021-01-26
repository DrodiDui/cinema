package by.kapitonov.cinema.backend.rest;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@Data
@EqualsAndHashCode
public class SortRequest {
    private Map<String, String> sortingParams;
}
