package com.isilona.hrm.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class PaymentInfo extends AbstractBaseEntity {

    @NotNull
    @Column(nullable = false)
    private String iban;

    @NotNull
    @Column(nullable = false)
    private BigDecimal salary;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Employee employee;
}
