package com.isilona.hrm.service;

import com.isilona.hrm.dao.entity.Employee;
import com.isilona.hrm.dao.repository.EmployeeRepository;
import com.isilona.hrm.dto.EmployeeDto;
import com.isilona.hrm.exception.ResourceNotFoundException;
import com.isilona.hrm.mapping.EmployeeMapper;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class EmployeeService extends AbstractBaseService<EmployeeRepository, EmployeeMapper, Employee, EmployeeDto> {

    public EmployeeService(EmployeeRepository repository, EmployeeMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public EmployeeDto update(EmployeeDto input) {
        Employee entity = getRepository().findByUuid(input.getUuid())
                .orElseThrow(() -> new ResourceNotFoundException(Employee.class, input.getUuid()));

        if (!isNull(input.getFirstName())) {
            entity.setFirstName(input.getFirstName());
        }
        if (!isNull(input.getLastName())) {
            entity.setLastName(input.getLastName());
        }

        Employee updated = getRepository().save(entity);
        return getMapper().entityToDto(updated);
    }
}
