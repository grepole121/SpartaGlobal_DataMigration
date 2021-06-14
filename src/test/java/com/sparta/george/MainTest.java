package com.sparta.george;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.List;


public class MainTest {
    @Test
    @DisplayName("Performance test to add 9943 entries to the database sequentially")
    public void performanceTestAddToDbSeq() throws FileNotFoundException, SQLException {
        List<EmployeeDTO> employeeDTOList = ReadFile.readFile(new FileReader("resources/employees.csv"));
        EmployeeDAO employeeDAO = new EmployeeDAO();
        employeeDAO.dropTable();
        employeeDAO.createTableIfNeeded();

        double startTime = System.nanoTime();

        employeeDAO.insertEmployee(employeeDTOList);

        double endTime = System.nanoTime();
        double timeTaken = (endTime - startTime) / 1000000000;
        System.out.printf("Time taken to add 9943 entries to the database sequentially: %.2f seconds\n", timeTaken);
    }

    @Test
    @DisplayName("Performance test to add 9943 entries to the database concurrently with 10 threads")
    public void performanceTestAddToDbConcurrently() throws FileNotFoundException, InterruptedException, SQLException {
        List<EmployeeDTO> employeeDTOList = ReadFile.readFile(new FileReader("resources/employees.csv"));
        EmployeeDAO employeeDAO = new EmployeeDAO();
        employeeDAO.dropTable();
        employeeDAO.createTableIfNeeded();
        int numberOfThreads = 10;

        double startTime = System.nanoTime();

        Starter.runConcurrently(employeeDAO, employeeDTOList, numberOfThreads);

        double endTime = System.nanoTime();
        double timeTaken = (endTime - startTime) / 1000000000;
        System.out.printf("Time taken to add 9943 entries to the database with %d threads: %.2f seconds\n", numberOfThreads, timeTaken);
    }

    @Test
    @DisplayName("Performance test to add 65499 entries to the database sequentially")
    public void performanceTestAddToDbSeqLarge() throws FileNotFoundException, SQLException {
        List<EmployeeDTO> employeeDTOList = ReadFile.readFile(new FileReader("resources/EmployeeRecordsLarge.csv"));
        EmployeeDAO employeeDAO = new EmployeeDAO();
        employeeDAO.dropTable();
        employeeDAO.createTableIfNeeded();
        int startingAmountOfEmployees = employeeDAO.countAllEmployees();

        double startTime = System.nanoTime();

        employeeDAO.insertEmployee(employeeDTOList);

        double endTime = System.nanoTime();
        double timeTaken = (endTime - startTime) / 1000000000;
        int endingAmountOfEmployees = employeeDAO.countAllEmployees();
        int employeesAdded = endingAmountOfEmployees - startingAmountOfEmployees;
        System.out.printf("Time taken to add %d entries to the database sequentially: %.2f seconds\n", employeesAdded, timeTaken);
    }

    @Test
    @DisplayName("Performance test to add 65499 entries to the database concurrently with 10 threads")
    public void performanceTestAddToDbConcurrentlyLarge() throws FileNotFoundException, InterruptedException, SQLException {
        List<EmployeeDTO> employeeDTOList = ReadFile.readFile(new FileReader("resources/EmployeeRecordsLarge.csv"));
        EmployeeDAO employeeDAO = new EmployeeDAO();
        employeeDAO.dropTable();
        employeeDAO.createTableIfNeeded();
        int startingAmountOfEmployees = employeeDAO.countAllEmployees();
        int numberOfThreads = 10;

        double startTime = System.nanoTime();

        Starter.runConcurrently(employeeDAO, employeeDTOList, numberOfThreads);

        double endTime = System.nanoTime();
        double timeTaken = (endTime - startTime) / 1000000000;
        int endingAmountOfEmployees = employeeDAO.countAllEmployees();
        int employeesAdded = endingAmountOfEmployees - startingAmountOfEmployees;
        System.out.printf("Time taken to add %d entries to the database with %d threads: %.2f seconds\n", employeesAdded, numberOfThreads, timeTaken);
    }

    @Test
    @DisplayName("Performance test to add 65499 entries to the database concurrently with 100 threads")
    public void performanceTestAddToDbConcurrentlyLarge100Threads() throws FileNotFoundException, InterruptedException, SQLException {
        List<EmployeeDTO> employeeDTOList = ReadFile.readFile(new FileReader("resources/EmployeeRecordsLarge.csv"));
        EmployeeDAO employeeDAO = new EmployeeDAO();
        employeeDAO.dropTable();
        employeeDAO.createTableIfNeeded();
        int startingAmountOfEmployees = employeeDAO.countAllEmployees();
        int numberOfThreads = 100;

        double startTime = System.nanoTime();

        Starter.runConcurrently(employeeDAO, employeeDTOList, numberOfThreads);

        double endTime = System.nanoTime();
        double timeTaken = (endTime - startTime) / 1000000000;
        int endingAmountOfEmployees = employeeDAO.countAllEmployees();
        int employeesAdded = endingAmountOfEmployees - startingAmountOfEmployees;
        System.out.printf("Time taken to add %d entries to the database with %d threads: %.2f seconds\n", employeesAdded, numberOfThreads, timeTaken);
    }
}
