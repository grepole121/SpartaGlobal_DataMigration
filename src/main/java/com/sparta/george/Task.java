package com.sparta.george;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.List;

public class Task implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());

        try {
            EmployeeDAO employeeDAO = new EmployeeDAO();
            FileReader csvFile = new FileReader("resources/employees.csv");
            employeeDAO.createTableIfNeeded();

            List<EmployeeDTO> employeeDTOList = Starter.readFile(csvFile);
            synchronized (this) {
                Starter.addToDb(employeeDAO, employeeDTOList);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
