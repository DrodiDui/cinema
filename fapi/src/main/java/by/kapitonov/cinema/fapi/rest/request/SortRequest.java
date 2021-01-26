package by.kapitonov.cinema.fapi.rest.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode
public class SortRequest {

    private Map<String, String> sortingParams;

}
