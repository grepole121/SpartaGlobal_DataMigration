package com.sparta.george;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.List;

public class Starter {
    public static int start(int numberOfThreads) throws SQLException, FileNotFoundException, InterruptedException {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        FileReader csvFile = new FileReader("resources/employees.csv");
        employeeDAO.createTableIfNeeded();
        int startingAmountOfEmployees = employeeDAO.countAllEmployees();

//        Sequential Method
//        employeeDAO.insertEmployee(readFile(csvFile));

//        Concurrent method
        runConcurrently(employeeDAO, readFile(csvFile), numberOfThreads);


        int endingAmountOfEmployees = employeeDAO.countAllEmployees();
        return endingAmountOfEmployees - startingAmountOfEmployees;
    }

    private static void runConcurrently(EmployeeDAO employeeDAO, List<EmployeeDTO> employeeDTOList, int numberOfThreads) throws InterruptedException {
        Thread[] threads = new Thread[numberOfThreads];

        int threadCounter = 0;
        for (int i = employeeDTOList.size() / numberOfThreads; i <= employeeDTOList.size(); i += employeeDTOList.size() / numberOfThreads) {
            int startOfSubList = i - employeeDTOList.size() / numberOfThreads;
            threads[threadCounter] = new Thread(new Task(employeeDAO, employeeDTOList.subList(startOfSubList, i)));
            threads[threadCounter].setName("Thread" + threadCounter);

            threads[threadCounter].start();
            threadCounter++;
        }
        for (Thread thread : threads) {
            thread.join();
        }
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
