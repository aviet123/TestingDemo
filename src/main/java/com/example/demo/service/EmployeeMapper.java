package com.example.demo.service;

import com.example.demo.entity.Employee;
import com.example.demo.model.EmployeeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper {

    EmployeeMapper mapper = Mappers.getMapper(EmployeeMapper.class);

    @Mappings({
            @Mapping(source ="id",target ="employeeId"),
            @Mapping(source="name",target="employeeName")
    })
    EmployeeDTO employeeToEmployeeDTO(Employee entity);
    @Mappings({
            @Mapping(source="employeeId",target="id"),
            @Mapping(source="employeeName",target="name")
    })
    Employee employeeDTOtoEmployee(EmployeeDTO dto);
}
