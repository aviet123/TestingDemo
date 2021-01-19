package com.example.demo.service;

import com.example.demo.model.EmployeeDetails;

public class EmpBusinessLogic {

    public double calculateYearSalary(EmployeeDetails employeeDetails){
        double yearSalary = 0;
        yearSalary = employeeDetails.getMonthSalary() * 12;
        return yearSalary;
    }

    public double calculateAppraisal( EmployeeDetails employeeDetails){
        double appraisal = 0;

        if (employeeDetails.getMonthSalary() < 1000){
            appraisal = 500;
        }else {
            appraisal = 1000;
        }
        return appraisal;
    }
}
