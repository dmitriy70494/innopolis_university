package ru.innopolis.lesson03.task03;

import java.util.Set;

public class ObjectBox<E> {
    Set<E> objects;

    public boolean addObject(E obj) {
        return objects.add(obj);
    }

    public boolean deleteObject(E obj) {
        return objects.remove(obj);
    }

    public void dump() {
        System.out.println(objects.toString());
    }
}
