package com.employee.ServiceImpl;

import com.employee.Entity.Employee;
import com.employee.Entity.EmployeePhoneNumber;
import com.employee.Exception.EmptyResultDataAccessException;
import com.employee.Exception.ResourceNotFoundException;
import com.employee.Payload.EmployeeDto;
import com.employee.Payload.EmployeePhoneNumberDto;
import com.employee.Repository.EmployeePhoneNumberRepository;
import com.employee.Service.EmployeePhoneNumberService;
import com.employee.Service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeePhoneNumberServiceImpl implements EmployeePhoneNumberService {

    private final ModelMapper modelMapper;
    private final EmployeePhoneNumberRepository employeePhoneNumberRepository;
    private final EmployeeService employeeService; // Inject EmployeeService

    @Autowired
    public EmployeePhoneNumberServiceImpl(EmployeePhoneNumberRepository employeePhoneNumberRepository,
                                          ModelMapper modelMapper,
                                          EmployeeService employeeService) {
        this.employeePhoneNumberRepository = employeePhoneNumberRepository;
        this.modelMapper = modelMapper;
        this.employeeService = employeeService;
    }
    @Override
    public EmployeePhoneNumberDto createEmployeePhoneNumber(EmployeePhoneNumberDto employeePhoneNumberDto) {
        Employee employee = employeeService.getEmployeeById(employeePhoneNumberDto.getEmployeeId());
        if (employee == null) {
            throw new ResourceNotFoundException("Employee with ID " + employeePhoneNumberDto.getEmployeeId() + " not found.");
        }
        EmployeePhoneNumber employeePhoneNumber = modelMapper.map(employeePhoneNumberDto, EmployeePhoneNumber.class);
        employeePhoneNumber.setEmployee(employee);
        EmployeePhoneNumber savedEmployeePhoneNumber = employeePhoneNumberRepository.save(employeePhoneNumber);
        return modelMapper.map(savedEmployeePhoneNumber, EmployeePhoneNumberDto.class);
    }

    @Override
    public EmployeePhoneNumberDto getEmployeePhoneNumberById(Long id) {
        EmployeePhoneNumber employeePhoneNumber = employeePhoneNumberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee phone number with ID " + id + " not found."));
        return modelMapper.map(employeePhoneNumber, EmployeePhoneNumberDto.class);
    }

    @Override
    public EmployeePhoneNumberDto updateEmployeePhoneNumber(Long id, EmployeePhoneNumberDto employeePhoneNumberDto) {
        EmployeePhoneNumber existingEmployeePhoneNumber = employeePhoneNumberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee phone number with ID " + id + " not found."));

        modelMapper.map(employeePhoneNumberDto, existingEmployeePhoneNumber);
        EmployeePhoneNumber updatedEmployeePhoneNumber = employeePhoneNumberRepository.save(existingEmployeePhoneNumber);
        return modelMapper.map(updatedEmployeePhoneNumber, EmployeePhoneNumberDto.class);
    }

    @Override
    public boolean deleteEmployeePhoneNumber(Long id) {
        if (employeePhoneNumberRepository.existsById(id)) {
            employeePhoneNumberRepository.deleteById(id);
            return true;
        } else {
            throw new ResourceNotFoundException("Employee phone number with ID " + id + " not found.");
        }
    }

    @Override
    public List<EmployeePhoneNumberDto> getAllEmployeePhoneNumbers() {
        List<EmployeePhoneNumber> employeePhoneNumbers = employeePhoneNumberRepository.findAll();
        return employeePhoneNumbers.stream()
                .map(employeePhoneNumber -> modelMapper.map(employeePhoneNumber, EmployeePhoneNumberDto.class))
                .collect(Collectors.toList());
    }
    public EmployeePhoneNumberDto convertToDto(EmployeePhoneNumber employeePhoneNumber) {
        return modelMapper.map(employeePhoneNumber, EmployeePhoneNumberDto.class);
    }

    public EmployeePhoneNumber convertToEntity(EmployeePhoneNumberDto employeePhoneNumberDto) {
        return modelMapper.map(employeePhoneNumberDto, EmployeePhoneNumber.class);
    }

}
