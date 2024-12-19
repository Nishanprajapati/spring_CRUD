package net.java.springboot_backend.service.impl;

import net.java.springboot_backend.service.EmailSenderService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import net.java.springboot_backend.DTO.EmployeeDTO;
import net.java.springboot_backend.Mapper.EmployeePopulator;
import net.java.springboot_backend.exception.EmployeeNotFoundException;
import net.java.springboot_backend.model.Address;
import net.java.springboot_backend.model.Employee;
import net.java.springboot_backend.repository.AddressRepository;
import net.java.springboot_backend.repository.EmployeeRepository;
import net.java.springboot_backend.service.AddressService;
import net.java.springboot_backend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {



    private  EmployeeRepository employeeRepository;
    private AddressService addressService;
    private EmailSenderService emailSenderService;
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
        Employee employee = EmployeePopulator.INSTANCE.populateEmployee(employeeDTO);

        //set address for employee
        Address address =new Address();
        address.setName("Kathmandu");
        employee.setAddress(address);

        //save address and employee
        addressService.addAddressToo(address);
        employee = employeeRepository.save(employee);

        ///sending email asynchronously
        emailSenderService.sendEmail("nisan11prajapati@gmail.com","This is subject","This is text");

        return employee;
    }

    @Override
//    @Transactional
//    @Cacheable(value = "employeeCache", key = "#id")
    public Employee getEmployeeById(long id) {
        // Retrieve an employee by ID


//       Employee updateEmployee = employeeRepository.findById(1L).get();
//        updateEmployee.setFirstName("kkkk");
//        updateEmployee.setLastName("hjjhb");
//        updateEmployee.setEmailId("vdjfk@gmail.com");
//        employeeRepository.save(updateEmployee);

        if (employeeRepository.findById(id).isEmpty()) {
            throw new EmployeeNotFoundException("Employee does not exist");
        }
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

    public void customQuery() {
        List<Employee> findEmployee =employeeRepository.getByFirstName("Nishan");
        findEmployee.forEach(e-> { System.out.println(e);});
    }

}
