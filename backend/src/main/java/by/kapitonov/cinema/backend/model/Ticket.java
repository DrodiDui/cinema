package by.kapitonov.cinema.backend.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tickets", schema = "cinema")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Ticket implements Serializable {

    private static final Long SERIAL_VERSION = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rows_number", nullable = false)
    private Integer rowsNumber;

    @Column(name = "number_seats_per_row", nullable = false)
    private Integer numberSeatsPerRow;

    @Column(name = "reserved")
    private Boolean reserved;

    @ManyToOne
    @JoinColumn(name = "film_session_id", referencedColumnName = "id")
    private FilmSession filmSession;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

}