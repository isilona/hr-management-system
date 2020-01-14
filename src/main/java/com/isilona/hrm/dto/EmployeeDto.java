package com.isilona.hrm.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

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
    private List<ContactDto> contacts;

    @Valid
    private PaymentInfoDto paymentInfo;

}
