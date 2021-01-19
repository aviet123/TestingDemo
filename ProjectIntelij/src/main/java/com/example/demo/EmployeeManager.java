package com.example.demo;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@AllArgsConstructor
public class EmployeeManager {
    private static final Logger LOGGER = LogManager.getLogger(EmployeeManager.class);
    private EmployeeRepository employeeRepository;
    public BankService bankService;

    public int payEmployee(){
        List<Employee> employees = employeeRepository.findAll();
        int payments = 0;
        for (Employee employee : employees) {
            try {
                bankService.pay(employee.getId(), employee.getSalary());
                employee.setPaid(true);
                payments++;
            }catch (RuntimeException e){
                LOGGER.error("Failed payment of " + employee, e);
                employee.setPaid(false);
            }
        }
        return payments;
    }

}
