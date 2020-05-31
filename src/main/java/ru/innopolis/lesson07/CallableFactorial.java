package ru.innopolis.lesson07;

import java.math.BigInteger;
import java.util.concurrent.Callable;

public class CallableFactorial implements Callable<Factorial> {
    private int number;

    CallableFactorial(int number) {
        this.number = number;
    }

    @Override
    public Factorial call() throws Exception {
        return new Factorial(this.number, this.getFactorial(this.number));
    }

    /*
     * Метод вычисляет факториал числа
     */
    private BigInteger getFactorial(int number) {
        BigInteger factorial = BigInteger.ONE;
        for (int i = 2; i < number + 1; i++) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }
        return factorial;
    }
}
