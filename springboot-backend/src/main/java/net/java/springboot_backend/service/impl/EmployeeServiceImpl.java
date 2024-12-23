package net.java.springboot_backend.service.impl;

import net.java.springboot_backend.dto.EmployeeDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import net.java.springboot_backend.service.EmailSenderService;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.Transactional;
import net.java.springboot_backend.mapper.EmployeePopulator;
import net.java.springboot_backend.exception.EmployeeNotFoundException;
import net.java.springboot_backend.model.Address;
import net.java.springboot_backend.model.Employee;
import net.java.springboot_backend.repository.EmployeeRepository;
import net.java.springboot_backend.service.AddressService;
import net.java.springboot_backend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@EnableCaching
@Service
public class EmployeeServiceImpl implements EmployeeService {



    private final EmployeeRepository employeeRepository;
    private final AddressService addressService;
    private final EmailSenderService emailSenderService;
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, AddressService addressService, EmailSenderService emailSenderService) {
        this.employeeRepository = employeeRepository;
        this.addressService = addressService;
        this.emailSenderService = emailSenderService;
    }

    @Override
    public Employee updateEmployee(long id, Employee employeeDetails) {
        if (employeeRepository.findById(id).isEmpty()) {
            throw new EmployeeNotFoundException("Employee does not exist");
        }
        Employee updateEmployee = employeeRepository.findById(id).get();
        updateEmployee.setFirstName(employeeDetails.getFirstName());
        updateEmployee.setLastName(employeeDetails.getLastName());
        updateEmployee.setEmailId(employeeDetails.getEmailId());
        employeeRepository.save(updateEmployee);
        return updateEmployee;
    }

    @Override
    @Transactional
    public Employee saveEmployee(EmployeeDTO employeeDTO) {
        // code to convert dto to entity
        System.out.println("Thread running saveEmployee method: " + Thread.currentThread().getName());
        Employee employee = EmployeePopulator.INSTANCE.populateEmployee(employeeDTO);

        Address address =new Address();
        address.setName("Kathmandu");
        employee.setAddress(address);
        addressService.addAddressToo(address);
        employee = employeeRepository.save(employee);

        emailSenderService.sendEmail("nisan11prajapati@gmail.com","This is subject","This is text");
        emailSenderService.sendEmail("nishan.201621@ncit.edu.np","This is subject","This is text");

        return employee;
    }

    @Override
    @Transactional
    @Cacheable(value = "employeeCache", key = "#id")
    public Employee getEmployeeById(long id) {

        if (employeeRepository.findById(id).isEmpty()) {
            throw new EmployeeNotFoundException("Employee does not exist");
        }

        Employee newEmployee = new Employee();
        newEmployee.setFirstName("ram");
        newEmployee.setLastName("hari");
        newEmployee.setEmailId("newemail@gmail.com");
        employeeRepository.save(newEmployee);

        return employeeRepository.findById(id).get();
    }

    @Override
    public void deleteEmployee(long id) {
        // Delete an employee by ID
        if (employeeRepository.findById(id).isEmpty()) {
            throw new EmployeeNotFoundException("Employee does not exist");
        }
        Employee existingEmployee = employeeRepository.findById(id).get();
        employeeRepository.delete(existingEmployee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        // Retrieve all employees
        return employeeRepository.findAll();
    }


    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    public void customQuery() {
        List<Employee> findEmployee = employeeRepository.getByFirstName("Nishan");
        findEmployee.forEach(e -> {
            logger.info("Employee: {}", e);
        });
    }




}
