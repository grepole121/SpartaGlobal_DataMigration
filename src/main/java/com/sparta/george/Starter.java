package com.sparta.george;

import java.sql.SQLException;
import java.util.List;

public class Starter {
    public static void start() throws SQLException {
        EmployeeDAO employeeDAO = new EmployeeDAO();
//        employeeDAO.createTableIfNeeded();

        List<EmployeeDTO> employeeDTOList = ReadFile.readFile();
        for (EmployeeDTO employeeDTO : employeeDTOList){
            employeeDAO.insertEmployee(employeeDTO);
        }
        employeeDAO.getAllEmployees();
    }
}
