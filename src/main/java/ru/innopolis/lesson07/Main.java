package ru.innopolis.lesson07;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        int[] numbers = new Random().ints(100, 1, 100).toArray();
        List<Future<Factorial>> factorials = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        for (int number: numbers) {
            factorials.add(executorService.submit(new CallableFactorial(number)));
        }
        factorials.forEach(e -> {
            try {
                System.out.println(e.get());
            } catch (InterruptedException | ExecutionException ex) {
                ex.printStackTrace();
            }
        });
        executorService.shutdown();
    }

}