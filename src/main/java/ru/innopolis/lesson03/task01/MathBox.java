package ru.innopolis.lesson03.task01;

import java.util.*;

public class MathBox {
    Set<Number> numbers;

    public MathBox(Number[] numbers) {
        this.numbers = new HashSet<>(Arrays.asList(numbers));
    }

    public double summator() {
        double result = 0.0;
        for(Number number : numbers) {
            result += number.doubleValue();
        }
        return result;
    }

    public void splitter(double divider) {
        Set<Number> result = new HashSet<>();
        for (Number number : this.numbers) {
            result.add(number.doubleValue() / divider);
        }
        this.numbers = result;
    }

    public boolean delete(Integer integer) {
        return this.numbers.remove(integer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MathBox)) return false;
        MathBox mathBox = (MathBox) o;
        return numbers.equals(mathBox.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbers);
    }

    @Override
    public String toString() {
        return "MathBox{" +
                "numbers=" + numbers +
                '}';
    }
}
