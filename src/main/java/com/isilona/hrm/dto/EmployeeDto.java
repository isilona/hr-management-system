package com.isilona.hrm.dto;

import com.isilona.hrm.dao.entity.Contact;
import com.isilona.hrm.dao.entity.PaymentInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
public class EmployeeDto extends BaseDto {

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;

    @Valid
    private AddressDto address;

    @Valid
    private Contact contact;

    @Valid
    private PaymentInfo paymentInfo;

}
