package com.isilona.hrm.dto;

import com.isilona.hrm.dao.entity.ContactType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
public class ContactDto extends BaseDto {

    @NotNull
    private ContactType type;

    @NotEmpty
    private String value;

}
