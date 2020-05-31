package ru.innopolis.lesson06.task2;

import ru.innopolis.lesson02.task03.GeneratePersons;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.UUID;

public class FileWriter {

    public void getFiles(String path, int n, int size, String[] words, int probability) {
        Paragraph paragraph = new Paragraph(words, probability);
        for (int i = 0; i < n; i++) {
            this.writeFile(path, this.getText(paragraph, size));
        }
    }

    public void writeFile(String folderPath, String text) {
        try {
            Files.write(Paths.get(folderPath + "\\" + UUID.randomUUID() + ".txt"), text.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getText(Paragraph paragraph, int size) {
        StringBuilder text = new StringBuilder();
        while (text.length() < size) {
            text.append(paragraph.getParagraph());
        }
        return text.substring(0, size);
    }

    public static void main(String[] args) {
        FileWriter generator = new FileWriter();
        generator.getFiles("C:\\TEMP", 2, 1000, new String[] {"I", "can", "eat", "now"}, 8);
    }
}
