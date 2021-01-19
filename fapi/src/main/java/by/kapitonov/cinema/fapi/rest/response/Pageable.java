package by.kapitonov.cinema.fapi.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Pageable {

    @JsonProperty("pageNumber")
    private int pageNumber;

}
