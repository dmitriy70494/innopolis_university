package ru.innopolis.lesson02.task03;

import java.util.Arrays;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        Sorter sorter = new SorterQuicklyImpl();
        Person[] persons = GeneratePersons.generatePersons();
        Comparator<Person> comparator = new Comparator<Person>() {
            @Override
            public int compare(Person person1, Person person2) {
                if (person1 != person2 && person1.getAge() == person2.getAge() && person1.getName().equals(person2.getName())) {
                    throw new UserIllegalException("имена людей и возраст совпадают" + person1 + person2);
                }
                int result = person1.getSex().compareTo(person2.getSex());
                if (result == 0) {
                    result = Integer.compare(person2.getAge(), person1.getAge());
                    if (result == 0) {
                        result = person1.getName().compareTo(person2.getName());
                    }
                }
                return result;
            }
        };
        long time = sorter.sortedArray(persons, comparator);
        System.out.println(Arrays.toString(persons));
        System.out.println("Время работы быстрой сортировки(мс) = " + time);
        sorter = new SorterShellImpl();
        persons = GeneratePersons.generatePersons();
        time = sorter.sortedArray(persons, comparator);
        System.out.println(Arrays.toString(persons));
        System.out.println("Время работы сортировки Шелла(мс) = " + time);
    }
}
