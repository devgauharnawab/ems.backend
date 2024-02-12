package net.fullstackproject.ems.service.impl;

import lombok.AllArgsConstructor;
import net.fullstackproject.ems.dto.EmployeeDto;
import net.fullstackproject.ems.entity.Employee;
import net.fullstackproject.ems.exception.ResourceNotFoundException;
import net.fullstackproject.ems.mapper.EmployeeMapper;
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

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
       Employee savedEmployee =  employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(long employeeId) {
     Employee employee =  employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee does not exist with the given id: " + employeeId));
         return EmployeeMapper.mapToEmployeeDto(employee);
    }
}
