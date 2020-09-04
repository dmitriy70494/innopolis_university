package ru.innopolis.lesson09;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Программа с консоли построчно считывает код метода doWork, считанные строки добавляются в тело метода public void doWork()
 * в файле SomeClass.java. Файл SomeClass.java компилируется программой (в рантайме) в файл SomeClass.class.
 * Полученный файл подгружается в программу с помощью кастомного загрузчика CustomClassLoader.
 * Метод, введенный с консоли, исполняется в рантайме (вызывается у экземпляра объекта подгруженного класса)
 *
 * @author Dmitriy_Balandin
 * @version 1.0.0
 */
public class Solution {
    /**
     * Метод для чтения кода метода doWork() класса lesson09.SomeClass из консоли
     *
     * @return - список с кодом класса lesson09.SomeClass
     * @throws IOException
     */
    private List<String> readSource() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Path sourcePatch = Paths.get("src/main/java/ru/innopolis/lesson09/SomeClass.java");
        List<String> someClassSourceList = Files.readAllLines(sourcePatch);

        String doWorkHeader = someClassSourceList.stream()
                .filter(s -> s.contains("public void doWork()"))
                .collect(Collectors.joining());

        int doWorkHeaderIndex = someClassSourceList.lastIndexOf(doWorkHeader);

        while (true) {
            String line = reader.readLine();
            if (line == null || line.equals("")) break;
            someClassSourceList.add(++doWorkHeaderIndex, line);
        }

        return someClassSourceList;
    }

    /**
     * Метод, сохраняющий файл SomeClass.java во временную директорию
     *
     * @param someClassSourceList - код файла SomeClass.java
     * @return - путь к файлу SomeClass.java во временной директории
     * @throws IOException
     */
    private Path saveSource(List<String> someClassSourceList) throws IOException {
        String tempDir = System.getProperty("java.io.tmpdir");
        Path sourcePath = Paths.get(tempDir, "SomeClass.java");
        Files.write(sourcePath, someClassSourceList);
        return sourcePath;
    }

    /**
     * Метод, компилирующий файл SomeClass.java в режиме runtime
     *
     * @param sourcePath - путь к файлу SomeClass.java во временной директории
     * @return - путь к файлу SomeClass.class во временной директории
     */
    private Path compileSource(Path sourcePath) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, null, null, sourcePath.toFile().getAbsolutePath());
        return sourcePath.getParent().resolve("SomeClass.class");
    }

    /**
     * Метод, подгружающий файл SomeClass.class в программу с помощью кастомного загрузчика CustomClassLoader
     *
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws IOException
     */
    public void runCustomClassLoader() throws ClassNotFoundException, IllegalAccessException,
            InstantiationException, IOException {
        Solution solution = new Solution();
        Path someClassFilePath = solution.compileSource(solution.saveSource(solution.readSource()));
        CustomClassLoader classLoader = new CustomClassLoader();
        classLoader.setPath(someClassFilePath);
        Class<?> someClass = classLoader.loadClass("ru.innopolis.lesson09.SomeClass");
        Worker worker = (Worker) someClass.newInstance();
        worker.doWork();
    }
}