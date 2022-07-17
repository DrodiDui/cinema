package com.kapitonau.cinema.directoryservice.model;


import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "directory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class DirectoryEntity {

    @Id
    @Column(name = "directory_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long directoryId;

    @Column(name = "directory_type")
    private String directoryType;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Type(type = "jsonb")
    @Column(name = "directory_attrs")
    private Map<String, Object> directoryAttrs;

}
