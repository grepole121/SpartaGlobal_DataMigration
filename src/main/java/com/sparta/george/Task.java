package com.sparta.george;

import java.io.FileNotFoundException;
import java.util.List;

public class Task implements Runnable {
    private List<EmployeeDTO> subList;
    private EmployeeDAO employeeDAO;

    public Task(EmployeeDAO employeeDAO, List<EmployeeDTO> subList) {
        this.subList = subList;
        this.employeeDAO = employeeDAO;
    }

    @Override
    public void run() {
        try {
            synchronized (this) {
                Starter.addToDbConcurrent(employeeDAO, subList);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
