package com.sparta.george;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class ReadFileTest {
    @Test
    @DisplayName("Performance test to read file with 10,000 entries")
    public void performanceTestReadFile1() throws FileNotFoundException {
        List<Double> times = new ArrayList<>();
        int numberOfRuns = 1;
        for (int i = 0; i < numberOfRuns; i++) {
            double startTime = System.nanoTime();

            ReadFile.readFile(new FileReader("resources/employees.csv"));

            double endTime = System.nanoTime();
            times.add((endTime - startTime) / 1000000);
        }
        double averageTime = times.stream().mapToDouble(Double::doubleValue).sum();
        System.out.printf("Average time taken to read file after %d runs: %.2fms\n", numberOfRuns, averageTime / numberOfRuns);
    }

    @Test
    @DisplayName("Performance test to read file with 10,000 entries")
    public void performanceTestReadFile10() throws FileNotFoundException {
        List<Double> times = new ArrayList<>();
        int numberOfRuns = 10;
        for (int i = 0; i < numberOfRuns; i++) {
            double startTime = System.nanoTime();

            ReadFile.readFile(new FileReader("resources/employees.csv"));

            double endTime = System.nanoTime();
            times.add((endTime - startTime) / 1000000);
        }
        double averageTime = times.stream().mapToDouble(Double::doubleValue).sum();
        System.out.printf("Average time taken to read file after %d runs: %.2fms\n", numberOfRuns, averageTime / numberOfRuns);
    }

    @Test
    @DisplayName("Performance test to read file with 10,000 entries")
    public void performanceTestReadFile100() throws FileNotFoundException {
        List<Double> times = new ArrayList<>();
        int numberOfRuns = 100;
        for (int i = 0; i < numberOfRuns; i++) {
            double startTime = System.nanoTime();

            ReadFile.readFile(new FileReader("resources/employees.csv"));

            double endTime = System.nanoTime();
            times.add((endTime - startTime) / 1000000);
        }
        double averageTime = times.stream().mapToDouble(Double::doubleValue).sum();
        System.out.printf("Average time taken to read file after %d runs: %.2fms\n", numberOfRuns, averageTime / numberOfRuns);
    }
}
