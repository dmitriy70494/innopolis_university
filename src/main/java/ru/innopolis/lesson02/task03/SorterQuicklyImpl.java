package ru.innopolis.lesson02.task03;

import java.util.Comparator;

public class SorterQuicklyImpl implements Sorter {

    @Override
    public long sortedArray(Person[] persons, Comparator<Person> comparator) {
        long start = System.currentTimeMillis();
        this.quickSort(persons, comparator, 0, persons.length - 1);
        return System.currentTimeMillis() - start;
    }

    private void quickSort(Person[] persons, Comparator<Person> comparator, int low, int high) {
        if (persons.length == 0) {
            return;
        }
        if (low >= high) {
            return;
        }
        int middle = low + (high - low) / 2;
        Person opora = persons[middle];
        int i = low, j = high;
        while (i <= j) {
            while (comparator.compare(persons[i], opora) < 0) {
                i++;
            }
            while (comparator.compare(persons[j], opora) > 0) {
                j--;
            }
            if (i <= j) {
                Person temp = persons[i];
                persons[i] = persons[j];
                persons[j] = temp;
                i++;
                j--;
            }
        }
        if (low < j)
            quickSort(persons, comparator, low, j);
        if (high > i)
            quickSort(persons, comparator, i, high);
    }
}
