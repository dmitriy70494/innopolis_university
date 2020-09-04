package ru.innopolis.lesson09;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Загрузчик для класса lesson09.SomeClass
 *
 * @author Dmitriy_Balandin
 * @version 1.0.0
 */
public class CustomClassLoader extends ClassLoader {
    private Path path;

    /**
     * Метод, устанавливающий путь к файлу SomeClass.class в переменную path
     *
     * @param path
     */
    public void setPath(Path path) {
        this.path = path;
    }

    /**
     * Метод для загрузки классов c заданным именем
     *
     * @param name - имя класса
     * @return - класс
     * @throws ClassNotFoundException
     */
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if ("ru.innopolis.lesson09.SomeClass".equals(name)) {
            return findClass(name);
        }
        return super.loadClass(name);
    }

    /**
     * Метод для поиска байт-кода SomeClass.class по пути path и создания экземпляра класса Class на его основе
     *
     * @param name - имя класса, который нужно создать (lesson09.SomeClass)
     * @return - класс
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if ("ru.innopolis.lesson09.SomeClass".equals(name)) {
            try {
                byte[] bytes = Files.readAllBytes(path);
                return defineClass(name, bytes, 0, bytes.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.findClass(name);
    }
}
