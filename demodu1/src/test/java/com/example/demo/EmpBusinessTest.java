package com.example.demo;

import com.example.demo.model.EmployeeDetails;
import com.example.demo.service.EmpBusinessLogic;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EmpBusinessTest {
    EmployeeDetails employee = new EmployeeDetails();
    EmpBusinessLogic businessLogic = new EmpBusinessLogic();

    @Test
    public void testCalculateAppraisal(){
        employee.setName("dinh viet");
        employee.setAge(25);
        employee.setMonthSalary(800);

        double appraisal = businessLogic.calculateAppraisal(employee);
        Assert.assertEquals(500,appraisal,0.0,"500");
    }

    @Test
    public void testCalculateYearSalary(){
        employee.setName("dinh viet");
        employee.setAge(30);
        employee.setMonthSalary(8000);

        double salary = businessLogic.calculateYearSalary(employee);
        Assert.assertEquals(96000, salary,0.0,"8000");
    }
}
