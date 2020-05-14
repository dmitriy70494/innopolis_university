package ru.innopolis.lesson02.task02;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class RandomNumber {

    public static void main(String[] args) {
        RandomNumber randomNumber = new RandomNumber();
        randomNumber.generateNumber();
    }

    /**
     * Для каждого числа массива вычислить квадратный корень q. Если квадрат целой части q числа равен числу массива,
     * то вывести это число на экран.
     *
     * @param numbers массив случайных чисел
     * @throws IllegalArgumentException возникает при отрацательных значениях в массиве
     */
    public void generateNumber(int[] numbers) throws IllegalArgumentException {
        for (int number : numbers) {
            if (this.checkNumberSqrt(number)) {
                System.out.println(number);
            }
        }
    }

    /**
     * Для каждого числа массива вычислить квадратный корень q. Если квадрат целой части q числа равен числу массива,
     * то вывести это число на экран. Изначальный массив чисел для проверки генерирует случайным образом.
     *
     * @throws IllegalArgumentException возникает при отрацательных значениях в массиве
     */
    public void generateNumber() {
        this.generateNumber(this.randomNumbers((int) (Math.random() * Integer.MAX_VALUE - 1)));
    }

    private boolean checkNumberSqrt(int number) throws IllegalArgumentException {
        if (number < 0) {
            throw new IllegalArgumentException("Отрицательное значение невозможно");
        }
        int sqrt = (int) Math.sqrt(number);
        return sqrt * sqrt == number;
    }

    private int[] randomNumbers(int size) {
        int[] numbers = new int[size];
        for (int i = 0; i < size - 1; i++) {
            numbers[i] = (int) (Math.random() * Integer.MAX_VALUE - 1);
        }
        return numbers;
    }
}
