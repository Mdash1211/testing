package com.employee.Controller;


import com.employee.Payload.EmployeePhoneNumberDto;
import com.employee.Service.EmployeePhoneNumberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empPhoneNo")
public class EmployeePhoneNumberController {
    private EmployeePhoneNumberService employeePhoneNumberService;
    @PostMapping
    public ResponseEntity<EmployeePhoneNumberDto> createEmployeePhoneNumber(@RequestBody EmployeePhoneNumberDto employeePhoneNumberDto) {
        EmployeePhoneNumberDto createdEmployeePhoneNumber = employeePhoneNumberService.createEmployeePhoneNumber(employeePhoneNumberDto);
        return new ResponseEntity<>(createdEmployeePhoneNumber, HttpStatus.CREATED);
    }

    // Get EmployeePhoneNumber by ID
    @GetMapping("/{id}")
    public ResponseEntity<EmployeePhoneNumberDto> getEmployeePhoneNumber(@PathVariable Long id) {
        EmployeePhoneNumberDto employeePhoneNumberDto = employeePhoneNumberService.getEmployeePhoneNumberById(id);
        if (employeePhoneNumberDto != null) {
            return new ResponseEntity<>(employeePhoneNumberDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update EmployeePhoneNumber
    @PutMapping("/{id}")
    public ResponseEntity<EmployeePhoneNumberDto> updateEmployeePhoneNumber(@PathVariable Long id, @RequestBody EmployeePhoneNumberDto employeePhoneNumberDto) {
        EmployeePhoneNumberDto updatedEmployeePhoneNumber = employeePhoneNumberService.updateEmployeePhoneNumber(id, employeePhoneNumberDto);
        if (updatedEmployeePhoneNumber != null) {
            return new ResponseEntity<>(updatedEmployeePhoneNumber, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete EmployeePhoneNumber
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployeePhoneNumber(@PathVariable Long id) {
        boolean deleted = employeePhoneNumberService.deleteEmployeePhoneNumber(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get all EmployeePhoneNumbers
    @GetMapping("/all")
    public ResponseEntity<List<EmployeePhoneNumberDto>> getAllEmployeePhoneNumbers() {
        List<EmployeePhoneNumberDto> employeePhoneNumbers = employeePhoneNumberService.getAllEmployeePhoneNumbers();
        return new ResponseEntity<>(employeePhoneNumbers, HttpStatus.OK);
    }
}


