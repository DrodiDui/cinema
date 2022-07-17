package com.kapitonau.cinema.directoryservice.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "directory_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DirectoryTypeEntity {

    @Id
    @Column(name = "directory_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long directoryTypeId;

    @Column(name = "directory_type")
    private String directoryType;

    @Column(name = "description")
    private String description;

}
