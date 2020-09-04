package ru.innopolis.lesson09;

/**
 * Класс, который компилируется в режиме runtime, а затем подгружается в программу с помощью
 * кастомного загрузчика CustomClassLoader
 *
 * @author Dmitriy_Balandin
 * @version 1.0.0
 */
public class SomeClass implements Worker {

    @Override
    public void doWork() {
        System.out.println("doWork");
    }
}
