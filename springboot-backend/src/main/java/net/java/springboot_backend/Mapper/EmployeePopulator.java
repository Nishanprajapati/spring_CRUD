package net.java.springboot_backend.mapper;

import net.java.springboot_backend.dto.EmployeeDto;
import net.java.springboot_backend.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeePopulator {
    EmployeePopulator INSTANCE = Mappers.getMapper(EmployeePopulator.class);
    @Mapping(target="id" , ignore=true)
    Employee populateEmployee(EmployeeDto employeeDTO);
}
