package com.sparta.george;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private final String URL = "jdbc:mysql://127.0.0.1:3306/mylocal";
    private Connection connection;

    private final String creatingTable = "CREATE TABLE IF NOT EXISTS Employees (" +
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
    private final String selectingEmployees = "SELECT * FROM Employees";
    private final String addEmployee = "INSERT INTO Employees VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE EmpID=EmpID;";

    public int insertEmployee(EmployeeDTO employeeDTO){
        int hasRun = 0;
        try (PreparedStatement preparedStatement = connectToDatabase().prepareStatement(addEmployee)){
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

            hasRun = preparedStatement.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }
        return hasRun;
    }

    public void createTableIfNeeded() throws SQLException {
        Statement statement = connectToDatabase().createStatement();
        statement.execute(creatingTable);
    }

    public void getAllEmployees() throws SQLException {
        Statement statement = connectToDatabase().createStatement();
        ResultSet resultSet = statement.executeQuery(selectingEmployees);
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        if (resultSet != null) {
            while (resultSet.next()) {
//                employeeList.add(new Employee(resultSet.getString(1),
//                        resultSet.getString(2),
//                        resultSet.getString(3),
//                        resultSet.getString(4),
//                        resultSet.getString(5),
//                        resultSet.getString(6),
//                        resultSet.getString(7),
//                        resultSet.getDate(8),
//                        resultSet.getDate(9),
//                        resultSet.getString(10)));
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


    private Connection connectToDatabase() {
        try {
            String password = System.getenv("db_password");
            connection = DriverManager.getConnection(URL, "root", password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
