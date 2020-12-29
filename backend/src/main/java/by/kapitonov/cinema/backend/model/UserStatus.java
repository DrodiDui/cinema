package by.kapitonov.cinema.backend.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_statuses", schema = "cinema")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserStatus implements Serializable {

    private static final Long SERIAL_VERSION = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status_name", nullable = false, length = 50)
    private String statusName;

    public UserStatus(String statusName) {
        this.statusName = statusName;
    }
}
