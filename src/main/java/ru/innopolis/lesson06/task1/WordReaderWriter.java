package ru.innopolis.lesson06.task1;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class WordReaderWriter {

    private Set<String> words = new HashSet<>();

    public void readFile(Path file) {
        try {
            Files.lines(file).forEach(this::setWordsFromString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeFile(Path path, Charset charset) {
        StringBuilder string = new StringBuilder();
       this.words.stream().sorted().forEach(word -> string.append(word).append("\r\n"));
        try {
            Files.write(path, string.toString().getBytes(charset));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setWordsFromString(String line) {
        Arrays.stream(line.split("[.;:!?, '\"(){}+]+")).forEach(str -> words.add(str.toLowerCase()));
    }
}
