package ru.innopolis.lesson06.task2;

import ru.innopolis.lesson02.task03.GeneratePersons;

import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.UUID;

public class TextGenerator {

    /**
     * Слово состоит из 1<=n2<=15 латинских букв
     */
    private static final int WORD_MAX_SIZE = 15;

    public void getFiles(String path, int n, int size, String[] words, int probability) {
        Queue<String> queueWords = this.getWords(words, probability, n * size + n * WORD_MAX_SIZE);//получить список слов по вероятности для файлов size*n
        List<GenFile> genFiles = this.getGenFiles(n, size, queueWords);//сгенерировать файлы size n
        genFiles.stream().forEach(gf -> gf.writeFile(path + "\\genFileN" + n + "Size" + size + UUID.randomUUID() + ".txt"));//записать в файлы
    }

    private Queue<String> getWords(String[] words, int probability, int allSize) {
        int countSize = 0;
        Queue<String> result = new LinkedList<>();
        while (countSize <= allSize) {
            String word = this.getProbablyWord(words, probability);
            result.offer(word);
            countSize += word.getBytes().length;
        }
        return result;
    }

    private String getProbablyWord(String[] words, int probability) {
        String result = "";
        this.prepareWords(words);
        while ("".equals(result)) {
            if ((double) 1/probability > Math.random()) {
                int i = (int) (words.length * Math.random());
                result = words[i];
            }
        }
        return result;
    }

    private void prepareWords(String[] words) {
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > WORD_MAX_SIZE) {
                words[i] = words[i].substring(0, WORD_MAX_SIZE);
            }
        }
    }

    private List<GenFile> getGenFiles(int n, int size, Queue<String> words) {
        List<GenFile> genFiles = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            genFiles.add(new GenFile().generateFile(words, size));
        }
        return genFiles;
    }

    public static void main(String[] args) {
        TextGenerator generator = new TextGenerator();
        generator.getFiles("C:\\TEMP", 2, 1000, new String[] {"я", "хочу", "есть", "fjglskdjflasjfsoifjroigfeoljesrgf"}, 8);
    }
}
