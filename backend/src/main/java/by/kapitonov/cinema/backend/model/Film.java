package by.kapitonov.cinema.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "films", schema = "cinema")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Film implements Serializable {

    private static final Long SERIAL_VERSION = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "film_name", nullable = false, length = 50)
    private String filmName;

    @Column(name = "year_of_release", nullable = false, length = 10)
    private String yearOfRelease;

    @Column(name = "genre", nullable = false, length = 25)
    private String genre;

    @Column(name = "country_of_origin", nullable = false, length = 50)
    private String countryOfOrigin;

    @Column(name = "director", nullable = false, length = 75)
    private String director;

    @Column(name = "starring", nullable = false, length = 150)
    private String starring;

    @Column(name = "budget", nullable = false)
    private Integer budget;

    @Column(name = "duration", nullable = false)
    private Integer duration;

    @OneToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private CinemaStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User owner;

    @OneToMany(mappedBy = "film", fetch = FetchType.LAZY)
    private List<FilmSession> filmSessions;
}