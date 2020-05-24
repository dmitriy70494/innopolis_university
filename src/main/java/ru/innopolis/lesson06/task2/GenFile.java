package ru.innopolis.lesson06.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Queue;

public class GenFile {

    /**
     *  в одном абзаце 1<=n3<=20 предложений
     */
    private static final int PARAGRAPH_MAX_SIZE = 20;

    /**
     * Предложение состоит из 1<=n1<=15 слов
     */
    private static final int SENTENCE_MAX_SIZE = 15;

    /**
     * Предложение заканчивается (.|!|?)+" "
     */
    private static final String[] SYMBOL_OF_END_SENTENCE = {".", "!", "?"};

    private StringBuilder file = new StringBuilder();

    private int size;

    private int maxSize;

    public GenFile generateFile(Queue<String> words, int maxSize) {
        this.maxSize = maxSize;
        while (size <= maxSize) {
            this.file.append(this.generateParagraph(words));
        }
        return this;
    }

    private StringBuilder generateParagraph(Queue<String> words) {
        StringBuilder paragraph = new StringBuilder();
        int countSentence = (int) (Math.random() * PARAGRAPH_MAX_SIZE + 1);
        for (int i = 0; i < countSentence; i++) {
            if (size < maxSize) {
                paragraph.append(this.generateSentence(words));
            }
        }
        paragraph.append(System.lineSeparator());
        size += System.lineSeparator().getBytes().length;
        return paragraph;
    }

    private StringBuilder generateSentence(Queue<String> words) {
        StringBuilder sentence = new StringBuilder();
        int countWord = (int) (Math.random() * SENTENCE_MAX_SIZE + 1);
        String word = words.poll();
        this.size += word.getBytes().length + 1;
        boolean isBreak = false;
        sentence.append(word.substring(0, 1).toUpperCase())
                .append(word.substring(1, word.length()))
                .append(" ");
        int randomEnd = (int) (Math.random() * 4 + 1);
        for (int i = 1; i < countWord; ++i) {
            word = words.poll();
            sentence.append(word).append(" ");
            this.size += word.getBytes().length + 1;
            if (size >= maxSize) {
                sentence.delete(sentence.length() - size + maxSize - 2, sentence.length());
                randomEnd = 2;
                break;
            }
        }
        sentence.append(this.genEndOfSentence(randomEnd));
        return sentence;
    }

    /*
     * Генерирует окончание предложения, согласно паттерну (.|!|?)+" "
     */
    private StringBuilder genEndOfSentence(int random) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < random - 1; i++) {
            result.append(SYMBOL_OF_END_SENTENCE[(int) (Math.random() * SYMBOL_OF_END_SENTENCE.length)]);
        }
        result.append(" ");
        size += random + 1;
        return result;
    }

    public void writeFile(String path) {
        try {
            Files.write(Paths.get(path), this.file.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
