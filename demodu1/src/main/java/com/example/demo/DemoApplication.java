package com.example.demo;

import com.example.demo.model.Employee;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

    private static String[] columns = {"Name","Email","Date Of Birth","Days Of Work","Salary Per day"};
    private static List<Employee> employees = new ArrayList<>();


    static {
        Calendar dateOfBirth = Calendar.getInstance();
        dateOfBirth.set(1996, Calendar.NOVEMBER, 5);
        employees.add(new Employee("viet", "dinhv2388@gmail.com", dateOfBirth.getTime(), 22d, 100d, null));
        dateOfBirth.set(1998, Calendar.JANUARY, 4);
        employees.add(new Employee("dinh quoc viet", "dinhv2388@gmail.com", dateOfBirth.getTime(), 21d, 122d, null));

        //create book
        Workbook workbook = new XSSFWorkbook();

        //create sheet
        Sheet sheet = workbook.createSheet("Employee");

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.AQUA.getIndex());

        //create cellstyle with the font
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(headerFont);

        //create row
        Row headerRow = sheet.createRow(0);

        //create cell depend on columns
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(cellStyle);
        }


        //formatting date
        CellStyle dateCellStyle = workbook.createCellStyle();
        CreationHelper creationHelper = workbook.getCreationHelper();
        dateCellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("dd-mm-yy"));

        int rowNum = 1;
        for (Employee employee : employees) {
            Row row = sheet.createRow(rowNum++);

            // Employee's name
            row.createCell(0)
                    .setCellValue(employee.getName());

            // Employee's email
            row.createCell(1)
                    .setCellValue(employee.getEmail());

            // Employee's date of birth
            Cell dateOfBirth1 = row.createCell(2);
            dateOfBirth1.setCellValue(employee.getDateOfBirth());
            dateOfBirth1.setCellStyle(dateCellStyle);

            // Employee's days of Work
            row.createCell(3, CellType.NUMERIC)
                    .setCellValue(employee.getDaysOfWork());

            // Employee's salary per day
            row.createCell(4, CellType.NUMERIC)
                    .setCellValue(employee.getSalaryPerDay());

            // Employee's total salary
            String formula = "D" + rowNum + " * E" + rowNum;
            row.createCell(5, CellType.FORMULA)
                    .setCellFormula(formula);

            FileOutputStream fileOutputStream;
            try {
                fileOutputStream = new FileOutputStream("employee.xlsx");
                workbook.write(fileOutputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
