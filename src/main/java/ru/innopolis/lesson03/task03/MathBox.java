package ru.innopolis.lesson03.task03;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class MathBox<E extends Number> extends ObjectBox<E>{

    public MathBox(E[] numbers) {
        this.objects = new HashSet<E>(Arrays.asList(numbers));
    }

    public double summator() {
        double result = 0.0;
        for(Number number : objects) {
            result += number.doubleValue();
        }
        return result;
    }

    public void splitter(E divider) {
        Set<E> result = new HashSet<>();
        for (E number : this.objects) {
            result.add((E) Double.valueOf(number.doubleValue() / divider.doubleValue()));
        }
        Double e;
        this.objects = result;
    }

    public boolean delete(Integer integer) {
        return this.objects.remove(integer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MathBox)) return false;
        MathBox mathBox = (MathBox) o;
        return objects.equals(mathBox.objects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(objects);
    }

    @Override
    public String toString() {
        return "MathBox{" +
                "numbers=" + objects +
                '}';
    }
}
