package com.isilona.hrm.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@Entity
public class Employee extends AbstractBaseEntity {

    @NotNull
    @Column(nullable = false)
    private String firstName;

    @NotNull
    @Column(nullable = false)
    private String lastName;

    @OneToOne(
            mappedBy = "employee",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private Address address;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "employee_contact",
            joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "contact_id", referencedColumnName = "id", nullable = false))
    private Set<Contact> contacts;

    @OneToOne(
            mappedBy = "employee",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private PaymentInfo paymentInfo;

    public void setAddress(Address address) {
        address.setEmployee(this);
        this.address = address;
    }

    public void setPaymentInfo(PaymentInfo paymentInfo) {
        paymentInfo.setEmployee(this);
        this.paymentInfo = paymentInfo;
    }

}
