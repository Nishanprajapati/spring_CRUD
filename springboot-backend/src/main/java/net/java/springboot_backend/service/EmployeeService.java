package net.java.springboot_backend.service;

import net.java.springboot_backend.dto.EmployeeDto;
import net.java.springboot_backend.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee updateEmployee( long id, Employee employeeDetails);
    Employee saveEmployee(EmployeeDto employeeDTO);
    Employee getEmployeeById(long id);
    void deleteEmployee(long id);
    List<Employee> getAllEmployees();
    void customQuery();
}
