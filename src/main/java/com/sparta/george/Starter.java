package com.sparta.george;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.List;

public class Starter {
    public static void start() throws SQLException, FileNotFoundException {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        FileReader csvFile = new FileReader("resources/employees.csv");
        employeeDAO.createTableIfNeeded();

        addToDb(employeeDAO, readFile(csvFile));

//        employeeDAO.getAllEmployees();
    }

    public static void addToDb(EmployeeDAO employeeDAO, List<EmployeeDTO> employeeDTOList) throws FileNotFoundException {
        double startTime = System.nanoTime();
        int added = 0;

        for (EmployeeDTO employeeDTO : employeeDTOList) {
            employeeDAO.insertEmployee(employeeDTO);
            added++;
        }

        double endTime = System.nanoTime();
        double timeTaken = (endTime - startTime) / 1000000000;
        System.out.printf("Time taken to add/update %d employees: %.2f seconds\n", added, timeTaken);
    }

    public static List<EmployeeDTO> readFile(FileReader fileReader) throws FileNotFoundException {
        double startTime = System.nanoTime();

        List<EmployeeDTO> employeeDTOList = ReadFile.readFile(fileReader);

        double endTime = System.nanoTime();
        double timeTaken = (endTime - startTime) / 1000000;
        System.out.printf("Time taken to read file: %.2f ms\n", timeTaken);

        return employeeDTOList;
    }
}
