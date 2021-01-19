package com.example.demo;

import org.assertj.core.api.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmployeeInMemoryRepositoryTest {
    private  EmployeeInMemoryRepository memoryRepository;

    private List<Employee> employees;

    @Before
    public void setup(){
        employees = new ArrayList<>();
        memoryRepository = new EmployeeInMemoryRepository(employees);
    }

    @Test
    public void testFindAll(){
        Employee employee1 = new Employee("1",1000);
        Employee employee2 = new Employee("2",1000);
        employees.addAll(Arrays.asList(employee1,employee2));
        assertThat(memoryRepository.findAll()).containsExactly(employee1,employee2);

    }

    @Test
    public void testSaveEmployee(){
        Employee saved = memoryRepository.save(new Employee("1",3000));
        assertThat(employees).containsExactly(saved);
    }

    @Test
    public void testSaveExistingEmployee(){
        Employee employee1 = new Employee("1",1000);
        Employee employee2 = new Employee("2",1000);
        employees.addAll(Arrays.asList(employee1,employee2));
//        Employee saved = memoryRepository.save(new Employee("3",3000));
        assertThat(employees).containsExactly(employee1,employee2);
    }
}
