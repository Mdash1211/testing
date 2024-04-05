package com.employee.Service;

import com.employee.Payload.EmployeePhoneNumberDto;

import java.util.List;

public interface EmployeePhoneNumberService {
    EmployeePhoneNumberDto createEmployeePhoneNumber(EmployeePhoneNumberDto employeePhoneNumberDto);

    EmployeePhoneNumberDto getEmployeePhoneNumberById(Long id);

    EmployeePhoneNumberDto updateEmployeePhoneNumber(Long id, EmployeePhoneNumberDto employeePhoneNumberDto);

    boolean deleteEmployeePhoneNumber(Long id);

    List<EmployeePhoneNumberDto> getAllEmployeePhoneNumbers();
}
