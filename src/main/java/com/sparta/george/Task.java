package com.sparta.george;

import java.util.List;

public class Task implements Runnable {
    private final List<EmployeeDTO> subList;
    private final EmployeeDAO employeeDAO;

    public Task(EmployeeDAO employeeDAO, List<EmployeeDTO> subList) {
        this.subList = subList;
        this.employeeDAO = employeeDAO;
    }

    @Override
    public void run() {
        synchronized (this) {
            employeeDAO.insertEmployee(subList);
        }
    }
}
