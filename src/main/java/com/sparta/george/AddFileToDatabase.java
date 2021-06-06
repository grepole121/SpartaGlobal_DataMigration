package com.sparta.george;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddFileToDatabase {
    public static int addFileToDatabase(FileReader fileReader, EmployeeDAO employeeDAO) {
        String line = "";
        int count = 0;
        try {
            BufferedReader br = new BufferedReader(fileReader);
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] employee = line.split(",");
                employeeDAO.insertEmployee(new EmployeeDTO(Integer.parseInt(employee[0]), employee[1], employee[2], employee[3], employee[4], employee[5], employee[6], new SimpleDateFormat("dd/MM/yyyy").parse(employee[7]), new SimpleDateFormat("dd/MM/yyyy").parse(employee[8]), Integer.parseInt(employee[9])));
                count++;
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return count;
    }
}
