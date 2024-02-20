package net.fullstackproject.ems.controller;

import lombok.AllArgsConstructor;
import net.fullstackproject.ems.dto.EmployeeDto;
import net.fullstackproject.ems.service.EmployeeService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//this class to handle Http request
@AllArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    //Build Add Employee REST API
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee( @RequestBody EmployeeDto employeeDto){
      EmployeeDto savedEmployee =   employeeService.createEmployee(employeeDto);
      return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }
    //Build GET Employee Rest API
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") long employeeId){
      EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
      return ResponseEntity.ok(employeeDto);
    }
    //Build Get All Employees REST API
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
        List<EmployeeDto> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }
    //Build Update Employees REST API
    @PutMapping("{id}") //The @RequestBody annotation is very useful for handling JSON requests.
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") long employeeId, @RequestBody EmployeeDto updatedEmployee){
      EmployeeDto employeeDto =  employeeService.updateEmployee(employeeId,updatedEmployee);
      return  ResponseEntity.ok(employeeDto);
    }
    //Delete Employee REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee( @PathVariable("id") long employeeId) {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Employee Deleted Successfully!.");
    }
}
