package com.employee.ServiceImpl;

import com.employee.Entity.Employee;
import com.employee.Exception.EmptyResultDataAccessException;
import com.employee.Exception.ResourceNotFoundException;
import com.employee.Payload.EmployeeDto;
import com.employee.Repository.EmployeeRepository;
import com.employee.Service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl  implements EmployeeService {

    private ModelMapper modelMapper;
    private EmployeeRepository employeeRepository;
    public EmployeeServiceImpl(EmployeeRepository employeeRepository,ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper=modelMapper;
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        // Generating a unique employeeId using UUID.randomUUID()
        String employeeId = UUID.randomUUID().toString();
        employeeDto.setEmployeeId(employeeId);

        Employee employee = convertToEntity(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return modelMapper.map(savedEmployee, EmployeeDto.class);
    }
    private EmployeeDto convertToDto(Employee employee) {
        return modelMapper.map(employee, EmployeeDto.class);
    }

    private Employee convertToEntity(EmployeeDto employeeDto) {
        return modelMapper.map(employeeDto, Employee.class);
    }

    }
