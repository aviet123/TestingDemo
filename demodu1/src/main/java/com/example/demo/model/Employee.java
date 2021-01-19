package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
public class Employee {
    private String name;
    private String email;
    private Date dateOfBirth;
    private double daysOfWork;
    private double salaryPerDay;
    private Double totalSalary;

}
