package com.employee.Service;

import com.employee.Entity.Employee;
import com.employee.Payload.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeDto employeeDto);

}
