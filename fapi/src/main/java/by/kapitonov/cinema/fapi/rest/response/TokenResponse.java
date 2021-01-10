package by.kapitonov.cinema.fapi.rest.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TokenResponse {

    private String token;
    private String email;
    private String role;

}
