package net.fullstackproject.ems.service.impl;

import net.fullstackproject.ems.dto.EmployeeDto;
import net.fullstackproject.ems.repository.EmployeeRepository;
import net.fullstackproject.ems.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service //this annotation tells spring container to create the spring Bean of this class
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        return null;
    }
}
