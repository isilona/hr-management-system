package com.isilona.hrm.mapping;

import com.isilona.hrm.dao.entity.Contact;
import com.isilona.hrm.dto.ContactDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContactMapper extends BaseMapper<ContactDto, Contact> {
}
