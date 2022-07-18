package com.kapitonau.cinema.adminservice.model.audit;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAuditEntity {

    @Column(name = "create_date")
    private ZonedDateTime createdDate = ZonedDateTime.now();

    @Column(name = "create_at")
    private String createdBy;

    @Column(name = "create_at_role")
    private String createdByRole;

    @Column(name = "update_date")
    private ZonedDateTime updatedDate;

    @Column(name = "update_at")
    private String updatedBy;

    @Column(name = "update_at_role")
    private String updatedByRole;

    @Column(name = "close_date")
    private ZonedDateTime closeDate;

}
