package net.fullstackproject.ems.service;

import net.fullstackproject.ems.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    EmployeeDto getEmployeeById(long employeeId);
    List<EmployeeDto> getAllEmployees();
    EmployeeDto updateEmployee(long employeeId,EmployeeDto updatedEmployee);
}
