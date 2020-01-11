package com.isilona.hrm.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
public class Address extends AbstractBaseEntity {

    @NotNull
    @Column(nullable = false)
    private String addressLine;

    @NotNull
    @Column(nullable = false)
    private String country;

    @NotNull
    @Column(nullable = false)
    private String city;

    @NotNull
    @Column(nullable = false)
    private String postCode;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Employee employee;

}
