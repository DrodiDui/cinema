package by.kapitonov.cinema.fapi.rest.response;

import by.kapitonov.cinema.fapi.exception.ApiErrorStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ApiExceptionResponse {

    private String exceptionMessage;
    private String errorStatus;
    private String timestamp;

}
