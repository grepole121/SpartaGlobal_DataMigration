package com.sparta.george;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.List;

public class Starter {
    public static void start(int numberOfThreads) throws SQLException, FileNotFoundException {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        FileReader csvFile = new FileReader("resources/employees.csv");
        employeeDAO.createTableIfNeeded();

//        Sequential Method
//        addToDb(employeeDAO, readFile(csvFile));

//        Concurrent method
        Thread thread = new Thread(new Task());
        thread.setName("Thread1");
        thread.start();

        Thread thread2 = new Thread(new Task());
        thread.setName("Thread2");
        thread.start();


//        List<EmployeeDTO> employeeDTOList = readFile(csvFile);
//        for (int i = employeeDTOList.size() / numberOfThreads; i <= employeeDTOList.size(); i += employeeDTOList.size() / numberOfThreads) {
//            int startOfSubList = i - employeeDTOList.size() / numberOfThreads;
//            Thread thread = new Thread(new Task());
//            System.out.println();
////            addToDb(employeeDAO, employeeDTOList.subList(startOfSubList, i));
//        }

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
