package com.isilona.hrm.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

@Data
@EqualsAndHashCode(callSuper = true)
public class AddressDto extends BaseDto {

    @NotEmpty
    private String addressLine;

    @NotEmpty
    private String country;

    @NotEmpty
    private String city;

    @NotEmpty
    private String postCode;

}
