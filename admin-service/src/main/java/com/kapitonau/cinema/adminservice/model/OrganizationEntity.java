package com.kapitonau.cinema.adminservice.model;

import com.kapitonau.cinema.adminservice.model.audit.AbstractAuditEntity;
import lombok.*;

import javax.persistence.*;

@Table(name = "organization")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrganizationEntity extends AbstractAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "org_id")
    private Long orgId;

    @Column(name = "org_name")
    private String orgName;

    @Column(name = "full_org_name")
    private String fullOrgName;

    @Column(name = "itn")
    private Long itn;

    @Column(name = "address")
    private String address;

    @Column(name = "illegal_address")
    private String illegalAddress;

    @Column(name = "status_id")
    private Long statusId;

    @Column(name = "phone_number")
    private String phoneNumber;

}
