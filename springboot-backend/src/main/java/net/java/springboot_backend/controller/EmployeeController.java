package net.java.springboot_backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import net.java.springboot_backend.dto.EmployeeDTO;
import net.java.springboot_backend.model.Employee;
import net.java.springboot_backend.service.EmployeeService;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.slf4j.Logger;



@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeservice) {
        this.employeeService = employeeservice;
    }

    //for creating employees RestAPI
    @Operation(summary = "ADD an employee", description = "For adding employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the employee"),
            @ApiResponse(responseCode = "404", description = "Employee not found")
    })
    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {

       Employee addEmployee = employeeService.saveEmployee(employeeDTO);
        return new ResponseEntity<>(addEmployee, HttpStatus.CREATED);
    }


    //build employee by id rest API
    @Operation(summary = "Get an employee by ID", description = "Retrieve a specific employee by their unique ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the employee"),
            @ApiResponse(responseCode = "404", description = "Employee not found")
    })
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    //build update employee REST API
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id, @RequestBody Employee employeeDetails) {
       Employee updateEmployee = employeeService.updateEmployee(id, employeeDetails);
        return ResponseEntity.ok(updateEmployee);
    }

    //build delete employee Rest API
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private final Logger logInfo = (Logger) LoggerFactory.getLogger(EmployeeController.class);
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        logInfo.info("Fetching all employees");
        logInfo.debug("Employee Debug Logging is enabled");
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/custom")
    public void customQuery() {
        employeeService.customQuery();
    }



}
