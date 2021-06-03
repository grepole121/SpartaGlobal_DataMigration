package com.sparta.george;

import java.sql.SQLException;
import java.util.List;

public class Starter {
    public static void start() throws SQLException {
        EmployeeDAO employeeDAO = new EmployeeDAO();
//        employeeDAO.createTableIfNeeded();

        List<Employee> employeeList = ReadFile.readFile();
        for (Employee employee: employeeList){
            employeeDAO.insertEmployee(employee);
        }
        employeeDAO.getAllEmployees();
    }
}
