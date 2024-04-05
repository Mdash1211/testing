package com.employee.Repository;

import com.employee.Entity.EmployeePhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeePhoneNumberRepository extends JpaRepository<EmployeePhoneNumber,Long> {
}
