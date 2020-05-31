package ru.innopolis.lesson07;

import java.math.BigInteger;


public class Factorial {
    private int number;
    private BigInteger factorial;

    Factorial(int number, BigInteger factorial) {
        this.number = number;
        this.factorial = factorial;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public BigInteger getFactorial() {
        return factorial;
    }

    public void setFactorial(BigInteger factorial) {
        this.factorial = factorial;
    }

    @Override
    public String toString() {
        return "Factorial{" +
                "number=" + number +
                ", factorial=" + factorial +
                '}';
    }
}
