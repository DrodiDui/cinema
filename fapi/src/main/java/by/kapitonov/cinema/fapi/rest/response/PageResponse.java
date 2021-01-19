package by.kapitonov.cinema.fapi.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PageResponse<T> {

    @JsonProperty("content")
    private List<T> content;
    @JsonProperty("totalElements")
    private int totalPages;
    @JsonProperty("totalPages")
    private long totalElements;

    private Pageable pageable;

    @JsonProperty("last")
    private boolean hasNext;
    @JsonProperty("first")
    private boolean hasPrevious;

}