package net.fullstackproject.ems.service.impl;

import jakarta.annotation.PostConstruct;
import net.fullstackproject.ems.dto.EmployeeDto;
import net.fullstackproject.ems.entity.Employee;
import net.fullstackproject.ems.exception.ResourceNotFoundException;
import net.fullstackproject.ems.mapper.EmployeeMapper;
import net.fullstackproject.ems.repository.EmployeeRepository;
import net.fullstackproject.ems.service.EmployeeService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
//import java.util.stream.Collectors;


@Service //this annotation tells spring container to create the spring Bean of this class
public class EmployeeServiceImpl implements EmployeeService {

    private  EmployeeRepository employeeRepository;


    public EmployeeServiceImpl(EmployeeRepository employeeRepository,JdbcTemplate jdbcTemplate) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        System.out.println("Employee created!!!!");
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

//    @Override
//    public List<EmployeeDto> getAllEmployees() {
//     List<Employee> employees =   employeeRepository.findAll();
//        return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
//                .collect(Collectors.toList());
//    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(EmployeeMapper::mapToEmployeeDto)
                .toList();
    }


    @Override
    public EmployeeDto updateEmployee(long employeeId, EmployeeDto updatedEmployee) {

     Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee is not exist with given id : " + employeeId)
        );
     employee.setFirstName(updatedEmployee.getFirstName());
     employee.setLastName(updatedEmployee.getLastName());
     employee.setEmail(updatedEmployee.getEmail());

    Employee updatedEmployeeObj =  employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
    }

    @Override
    public void deleteEmployee(long employeeId) {
        System.out.println("Employee deleted!!!!");

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee is not exist with given id : " + employeeId)
        );
        employeeRepository.deleteById(employeeId);
    }
}
