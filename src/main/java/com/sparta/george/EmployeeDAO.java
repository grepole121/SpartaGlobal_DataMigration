package com.sparta.george;

import java.sql.*;
import java.util.List;

public class EmployeeDAO {
    private Connection connection;

    public void insertEmployee(List<EmployeeDTO> employeeDTOList) {
        String addEmployee = "INSERT INTO Employees VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE EmpID=EmpID;";
        try (PreparedStatement preparedStatement = connectToDatabase().prepareStatement(addEmployee)) {
            for (EmployeeDTO employeeDTO : employeeDTOList) {
                preparedStatement.setInt(1, employeeDTO.getEmpID());
                preparedStatement.setString(2, employeeDTO.getNamePrefix());
                preparedStatement.setString(3, employeeDTO.getFirstName());
                preparedStatement.setString(4, employeeDTO.getMiddleInitial());
                preparedStatement.setString(5, employeeDTO.getLastName());
                preparedStatement.setString(6, employeeDTO.getGender());
                preparedStatement.setString(7, employeeDTO.getEmail());
                preparedStatement.setDate(8, new java.sql.Date(employeeDTO.getDateOfBirth().getTime()));
                preparedStatement.setDate(9, new java.sql.Date(employeeDTO.getDateOfJoining().getTime()));
                preparedStatement.setInt(10, employeeDTO.getSalary());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createTableIfNeeded() throws SQLException {
        Statement statement = connectToDatabase().createStatement();
        String creatingTable = "CREATE TABLE IF NOT EXISTS Employees (" +
                "EmpID int NOT NULL, " +
                "namePrefix VARCHAR(10), " +
                "firstName VARCHAR(30), " +
                "middleInitial CHAR(1), " +
                "lastName VARCHAR(30), " +
                "gender CHAR(1), " +
                "email VARCHAR(50), " +
                "dateOfBirth DATE, " +
                "dateOfJoining DATE, " +
                "salary int, " +
                "PRIMARY KEY (EmpID));";
        statement.execute(creatingTable);
    }

    public void getAllEmployees() throws SQLException {
        Statement statement = connectToDatabase().createStatement();
        String selectingEmployees = "SELECT * FROM Employees";
        ResultSet resultSet = statement.executeQuery(selectingEmployees);
        if (resultSet != null) {
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " " +
                        resultSet.getString(2) + " " +
                        resultSet.getString(3) + " " +
                        resultSet.getString(4) + " " +
                        resultSet.getString(5) + " " +
                        resultSet.getString(6) + " " +
                        resultSet.getString(7) + " " +
                        resultSet.getDate(8) + " " +
                        resultSet.getDate(9) + " " +
                        resultSet.getString(10));
            }
        } else {
            System.out.println("No records in the table");
        }

    }


    public int countAllEmployees() throws SQLException {
        Statement statement = connectToDatabase().createStatement();
        String countingEmployees = "SELECT COUNT(*) FROM Employees";
        ResultSet resultSet = statement.executeQuery(countingEmployees);
        if (resultSet != null) {
            while (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } else {
            System.out.println("No records in the table");
        }
        return 0;
    }


    private Connection connectToDatabase() {
        try {
            String password = System.getenv("db_password");
            String URL = "jdbc:mysql://127.0.0.1:3306/mylocal";
            connection = DriverManager.getConnection(URL, "root", password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
