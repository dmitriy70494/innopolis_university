package ru.innopolis.lesson02;

import java.util.Scanner;

public class ExceptionBuilder {

    public static void main(String[] args) {
        System.out.println("Hellow Word! введите в консоль значение от 1 до 3 для вызова ошибки");
        Scanner inScanner = new Scanner(System.in);
        int number = inScanner.nextInt();
        inScanner.close();
        if (number == 1) {
            inScanner = null;
            inScanner.nextInt();
        } else if (number == 2) {
            int[] massive = new int[1];
            int i = massive[1];
        } else if (number == 3) {
            throw new UserRuntimeException("Введен параметр 3");
        }
    }
}
