package ru.innopolis.lesson06.task1;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {
        System.out.println("DEFINE QL('EQ_WBI_MR1.OP1.Q') +".split("[ ].")[0]);
        WordReader reader = new WordReader();
        String textFileName = "textLesson06Task1.tst";
        String resultFileName = "resultLesson06Task1.tst";
        Path textDirectory = Paths.get("src","main","resources", textFileName);
        Path resultDirectory = Paths.get("src","main","resources", resultFileName);
        reader.readFile(textDirectory);
        reader.writeFile(
                resultDirectory,
                Charset.forName("cp1251")
        );
    }
}
