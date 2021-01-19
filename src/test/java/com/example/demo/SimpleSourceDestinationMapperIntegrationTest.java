package com.example.demo;

import com.example.demo.entity.Employee;
import com.example.demo.entity.SimpleSource;
import com.example.demo.model.EmployeeDTO;
import com.example.demo.model.SimpleDTODestination;
import com.example.demo.service.EmployeeMapper;
import com.example.demo.service.SimpleSourceDestination;
import com.example.demo.service.SimpleSourceDestinationMapperImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

public class SimpleSourceDestinationMapperIntegrationTest {

    private SimpleSourceDestination simple = new SimpleSourceDestinationMapperImpl();

    @Test
    public void givenSourceToDestination_whenMaps_thenCorrect(){
        SimpleSource simpleSource = Mockito.mock(SimpleSource.class);
        simpleSource.setName("SourceName");
        simpleSource.setDescription("SourceDescription");
        SimpleDTODestination destination = simple.sourceToDestination(simpleSource);

        Assert.assertEquals(simpleSource.getDescription(),destination.getDescription());
    }

    @Test
    public void givenEmployeeDTOwithDiffNametoEmployee_whenMaps_thenCorrect(){
        EmployeeDTO dto = new EmployeeDTO();
        dto.setEmployeeId(1);
        dto.setEmployeeName("Viet");

        Employee employee = EmployeeMapper.mapper.employeeDTOtoEmployee(dto);

        Assert.assertEquals(dto.getEmployeeId(),employee.getId());
        Assert.assertEquals(dto.getEmployeeName(),employee.getName());
    }

}
