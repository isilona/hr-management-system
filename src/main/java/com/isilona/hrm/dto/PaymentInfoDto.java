package com.isilona.hrm.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class PaymentInfoDto extends BaseDto {

    @NotEmpty
    private String iban;

    @NotNull
    private BigDecimal salary;

}
