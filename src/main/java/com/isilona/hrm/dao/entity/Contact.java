package com.isilona.hrm.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
public class Contact extends AbstractBaseEntity {

    @NotNull
    @Enumerated(EnumType.STRING)
    private ContactType type;

    @NotNull
    @Column(nullable = false)
    private String value;

}
