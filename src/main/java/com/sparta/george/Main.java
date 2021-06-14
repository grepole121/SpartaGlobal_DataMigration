package com.sparta.george;


import java.io.FileNotFoundException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, FileNotFoundException, InterruptedException {
        double startTime = System.nanoTime();

        int added = Starter.start(10);

        double endTime = System.nanoTime();
        double timeTaken = (endTime - startTime) / 1000000000;
        System.out.printf("\n\nTime taken for the program to read the file and add %d employees: %.2f seconds\n", added, timeTaken);

    }
}
