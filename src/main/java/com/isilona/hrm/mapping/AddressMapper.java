package com.isilona.hrm.mapping;

import com.isilona.hrm.dao.entity.Address;
import com.isilona.hrm.dto.AddressDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper extends BaseMapper<AddressDto, Address> {
}
