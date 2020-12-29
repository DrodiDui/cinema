package by.kapitonov.cinema.backend.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "halls", schema = "cinema")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Hall implements Serializable {

    private static final Long SERIAL_VERSION = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hall_name", nullable = false, length = 50)
    private String hallName;

    @Column(name = "floor", nullable = false)
    private Integer floor;

    @Column(name = "rows_number", nullable = false)
    private Integer rowsNumbers;

    @Column(name = "number_seats_per_row", nullable = false)
    private Integer numberSeatsPerRow;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private CinemaStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cinema_id", referencedColumnName = "id")
    private Cinema cinema;

}