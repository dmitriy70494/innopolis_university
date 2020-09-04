package ru.innopolis.lesson09;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class Main {

    public static void main(String[] args) {
        final InputStream systemIn = System.in;
        ByteArrayInputStream testIn;
        final String testString = "System.out.println(\"new doWork\");";
        testIn = new ByteArrayInputStream(testString.getBytes());
        System.setIn(testIn);
        try {
            Solution solution = new Solution();
            solution.runCustomClassLoader();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.setIn(systemIn);
        }
    }
}
