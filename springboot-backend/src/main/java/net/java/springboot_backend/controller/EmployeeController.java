package net.java.springboot_backend.controller;

import net.java.springboot_backend.DTO.EmployeeDTO;
import net.java.springboot_backend.model.Employee;
import net.java.springboot_backend.repository.AddressRepository;
import net.java.springboot_backend.repository.EmployeeRepository;
import net.java.springboot_backend.service.EmployeeService;
import net.java.springboot_backend.service.impl.EmployeeServiceImpl;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private EmployeeService employeeservice;
    public EmployeeController(EmployeeService employeeservice) {
        this.employeeservice = employeeservice;
    }



    private Logger logInfo = (Logger) LoggerFactory.getLogger(EmployeeController.class);
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        logInfo.info("Fetching all employees");
        logInfo.debug("Employee Debug Logging is enabled");
        return ResponseEntity.ok(employeeservice.getAllEmployees());
    }

    //for creating employees RestAPI
    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody EmployeeDTO employeeDTO) {

       Employee addEmployee = employeeservice.saveEmployee(employeeDTO);
        return new ResponseEntity<>(addEmployee, HttpStatus.CREATED);
    }


    //build employee by id rest API
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id) {
        return ResponseEntity.ok(employeeservice.getEmployeeById(id));
    }

    //build update employee REST API
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id, @RequestBody Employee employeeDatails) {
       Employee updateEmployee = employeeservice.updateEmployee(id, employeeDatails);
        return ResponseEntity.ok(updateEmployee);
    }

    //build delete employee Rest API
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable long id) {
        employeeservice.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/custom")
    public void customQuery() {
        employeeservice.customQuery();
    }
}
