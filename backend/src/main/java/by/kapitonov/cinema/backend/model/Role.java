package by.kapitonov.cinema.backend.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "roles", schema = "cinema")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Role {

    private static final Long SERIAL_VERSION = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name", nullable = false, length = 50)
    private String roleName;

    public Role(String roleName) {
        this.roleName = roleName;
    }
}
