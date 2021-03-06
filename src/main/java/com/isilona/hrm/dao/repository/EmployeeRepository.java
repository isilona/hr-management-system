package com.isilona.hrm.dao.repository;

import com.isilona.hrm.dao.entity.Employee;
import com.isilona.hrm.dao.entity.QEmployee;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends BaseRepository<Employee, QEmployee> {
}
