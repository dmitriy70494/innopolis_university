package ru.innopolis.lesson02.task03;

import java.util.Comparator;
import java.util.List;

public interface Sorter {

    /**
     * Сортирует элементы в переданном массиве согласно Comporator-у.
     *
     * @param persons - неотсортированный массив Person
     * @param comparator - сравнение объектов больше-меньше
     * @return long время работы сортировки в мс.
     */
    long sortedArray(Person[] persons, Comparator<Person> comparator);
}
