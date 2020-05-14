package ru.innopolis.lesson02.task03;

import java.util.Comparator;

public class SorterShellImpl implements Sorter {

    @Override
    public long sortedArray(Person[] persons, Comparator<Person> comparator) {
        long start = System.currentTimeMillis();
        this.shellSort(persons, comparator);
        return System.currentTimeMillis() - start;
    }

    private void shellSort(Person[] persons, Comparator<Person> comparator) {
        int inner, outer;
        Person temp;

        int h = 1;
        while (h <= persons.length / 3) {
            h = h * 3 + 1;
        }
        while (h > 0) {
            for (outer = h; outer < persons.length; outer++) {
                temp = persons[outer];
                inner = outer;

                while (inner > h - 1 && comparator.compare(persons[inner - h], temp) >= 0) {
                    persons[inner] = persons[inner - h];
                    inner -= h;
                }
                persons[inner] = temp;
            }
            h = (h - 1) / 3;
        }
    }
}
