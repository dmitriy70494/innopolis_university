package ru.innopolis.lesson08;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import ru.innopolis.lesson02.task03.Sex;

/**
 * Класс, сериализующий объекты в *.json и десериализующий их обратно
 *
 * @author Dmitriy_Balandin
 * @version 1.0.0
 */
public class Serializer {
    private List<String> jsonList = new ArrayList<>();

    /**
     * Метод для записи имен и значений атрибутов объектов в jsonList
     *
     * @param object - объект для сериализации в *.json
     */
    private void getObjectFields(Object object) {
        Stream.of(object.getClass().getDeclaredFields()).forEachOrdered(field -> {
            field.setAccessible(true);
            if (!field.isEnumConstant() && !field.isSynthetic()) {
                try {
                    if (field.getType().toString().contains("class") && field.get(object) != null
                            && !field.getType().toString().contains("java.lang.String")) {
                        writeObjectToJson(object, field);
                    } else {
                        writePrimitiveToJson(object, field);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Метод для записи имен и значений ссылочных типов в jsonList
     *
     * @param object - объект для сериализации в *.json
     * @param field  - поле ссылочного типа
     * @throws IllegalAccessException
     */
    private void writeObjectToJson(Object object, Field field) throws IllegalAccessException {
        jsonList.add("\t" + "\"" + field.getName() + "\": {");
        getObjectFields(field.get(object));
        jsonList.set(jsonList.size() - 1, jsonList.get(jsonList.size() - 1).replace(",", ""));
        jsonList.add("\t" + "},");
    }

    /**
     * Метод для записи имен и значений примитивов в jsonList
     *
     * @param object - объект для сериализации в *.json
     * @param field  - поле примитивного типа
     * @throws IllegalAccessException
     */
    private void writePrimitiveToJson(Object object, Field field) throws IllegalAccessException {
        jsonList.add("\t" + "\"" + field.getName() + "\": \"" + field.get(object) + "\",");
    }

    /**
     * Метод для для сериализации объектов в *.json
     *
     * @param object - объект для сериализации в *.json
     * @param file   - путь к *.json
     */
    void serialize(Object object, String file) throws IOException {
        jsonList.clear();
        jsonList.add("{");
        getObjectFields(object);
        jsonList.add("}");
        jsonList.set(jsonList.size() - 2, jsonList.get(jsonList.size() - 2).replace(",", ""));
        Files.write(Paths.get(file), jsonList, StandardCharsets.UTF_8);
    }

    /**
     * Метод для для десериализации объектов из *.json
     *
     * @param file - путь к *.json
     * @return объект на основе данных из *.json
     */
    Object deSerialize(String file) throws IOException, ClassNotFoundException, InstantiationException,
            IllegalAccessException, InvocationTargetException {
        List<String> text = Files.readAllLines(Paths.get(file));
        String className = file.substring(file.lastIndexOf("\\") + 1, file.length() - 5);

        String ageLine = text.get(1);
        String sexLine = text.get(3);
        String nameLine = text.get(5);

        int personAge = Integer.parseInt(ageLine.substring(ageLine.lastIndexOf(": ") + 3, ageLine.length() - 2));
        String enumValue = sexLine.substring(sexLine.lastIndexOf(": ") + 3, sexLine.length() - 1);
        Sex personSex = Sex.valueOf(enumValue.toUpperCase());
        String personName = nameLine.substring(nameLine.lastIndexOf(": ") + 3, nameLine.length() - 2);

        Constructor[] constructors = Objects.requireNonNull(Class.forName(className)).getConstructors();
        Object objectDto = constructors[0].newInstance(personAge, personSex, personName);

        return objectDto;
    }
}
