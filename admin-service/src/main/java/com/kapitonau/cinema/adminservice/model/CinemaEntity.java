package com.kapitonau.cinema.adminservice.model;

import com.kapitonau.cinema.adminservice.model.audit.AbstractAuditEntity;
import lombok.*;

import javax.persistence.*;

@Table(name = "cinema")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CinemaEntity extends AbstractAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cinema_id")
    private Long cinemaId;

    @Column(name = "cinema_name", nullable = false, length = 50)
    private String cinemaName;

    @Column(name = "country", nullable = false, length = 50)
    private String country;

    @Column(name = "city", nullable = false, length = 50)
    private String city;

    @Column(name = "address", nullable = false, length = 50)
    private String address;

    @Column(name = "description", nullable = false, length = 200)
    private String description;

    @Column(name = "status_id")
    private Long statusId;

    @Column(name = "org_id")
    private Long orgId;

}
