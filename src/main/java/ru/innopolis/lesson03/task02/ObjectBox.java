package ru.innopolis.lesson03.task02;

import java.util.Set;

public class ObjectBox {
    Set<Object> objects;

    public boolean addObject(Object obj) {
        return this.objects.add(obj);
    }

    public boolean deleteObject(Object obj) {
        return this.objects.remove(obj);
    }

    public void dump() {
        System.out.println(this.objects.toString());
    }
}
