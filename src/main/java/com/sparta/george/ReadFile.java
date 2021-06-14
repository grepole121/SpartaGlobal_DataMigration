package com.sparta.george;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {

    public static List<EmployeeDTO> readFile(FileReader fileReader) {
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        String line;
        try {
            BufferedReader br = new BufferedReader(fileReader);
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] employee = line.split(",");
                employeeDTOList.add(new EmployeeDTO(Integer.parseInt(employee[0]), employee[1], employee[2], employee[3], employee[4], employee[5], employee[6], new SimpleDateFormat("dd/MM/yyyy").parse(employee[7]), new SimpleDateFormat("dd/MM/yyyy").parse(employee[8]), Integer.parseInt(employee[9])));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return employeeDTOList;
    }
}