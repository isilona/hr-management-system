package com.isilona.hrm.mapping;

import com.isilona.hrm.dao.entity.Employee;
import com.isilona.hrm.dto.EmployeeDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface EmployeeMapper extends BaseMapper<EmployeeDto, Employee> {

}
