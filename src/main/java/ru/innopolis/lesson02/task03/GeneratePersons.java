package ru.innopolis.lesson02.task03;

public class GeneratePersons {

    private static final String[] NAMES = {
            "Дмитрий", "Владимир", "Валерий", "Евгений", "Александр", "Акакий",
            "Владлен", "Иосиф", "Иван", "Савва", "name1", "name2", "name3", "name4",
            "name5", "name6", "name7", "name8", "name9", "name10",
            "Дмитрий2", "Владимир2", "Валерий2", "Евгений2", "Александр2", "Акакий2",
            "Владлен2", "Иосиф2", "Иван2", "Савва2", "name12", "name22", "name32", "name42",
            "name52", "name62", "name72", "name82", "name92", "name102",
            "Дмитрий3", "Владимир3", "Валерий3", "Евгений3", "Александр3", "Акакий3",
            "Владлен3", "Иосиф3", "Иван3", "Савва3", "name13", "name23", "name33", "name43",
            "name53", "name63", "name73", "name83", "name93", "name103",
            "Дмитрий4", "Владимир4", "Валерий4", "Евгений4", "Александр4", "Акакий4",
            "Владлен4", "Иосиф4", "Иван4", "Савва4", "name14", "name24", "name34", "name44",
            "name54", "name64", "name74", "name84", "name94", "name104",
            "Дмитрий5", "Владимир5", "Валерий5", "Евгений5", "Александр5", "Акакий5",
            "Владлен5", "Иосиф5", "Иван5", "Савва5", "name15", "name25", "name35", "name45",
            "name55", "name65", "name75", "name85", "name95", "name1052"
    };

    public static Person[] generatePersons() {
        Person[] persons = new Person[10000];
        int count = 0;
        for (int age = 0; age < 100; age++) {
            for (String name : NAMES) {
                persons[count++] = new Person(age, Math.random() > 0.5 ? Sex.MAN : Sex.WOMAN, name);
            }
        }
        return persons;
    }
}
