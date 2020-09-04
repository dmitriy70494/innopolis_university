package ru.innopolis.lesson08;


import ru.innopolis.lesson02.task03.Person;
import ru.innopolis.lesson02.task03.Sex;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        Serializer serializer = new Serializer();
        Person person = new Person(27, Sex.MAN, "John Smith");
        Path path = Paths.get("src","main","resources", Person.class.getName() + ".json");
        System.out.println(person);
        try {
            serializer.serialize(person, path.toString());
            System.out.println(serializer.deSerialize(path.toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
