package net.java.springboot_backend.Mapper;

import net.java.springboot_backend.DTO.EmployeeDTO;
import net.java.springboot_backend.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeePopulator {
    EmployeePopulator INSTANCE = Mappers.getMapper(EmployeePopulator.class);
    @Mapping(target="id" , ignore=true)
    Employee populateEmployee(EmployeeDTO employeeDTO);
}
