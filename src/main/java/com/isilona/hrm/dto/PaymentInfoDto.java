package com.isilona.hrm.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class PaymentInfoDto extends BaseDto {

    @NotNull
    private UUID employeeId;

    @NotEmpty
    private String iban;

    @NotNull
    private BigDecimal salary;

}
