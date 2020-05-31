package ru.innopolis.lesson06.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class Word {

    /**
     * Слово состоит из 1<=n2<=15 латинских букв
     */
    private static final int WORD_MAX_SIZE = 15;

    /**
     * Список слов
     */
    private String[] words;

    /**
     * вероятность попадания числа из списка
     */
    private float probability;

    /**
     * Генератор случайных значний
     */
    private Random random;

    public Word(String[] words, int probability) {
        this.words = words;
        this.probability = 1f / probability;
        random = new Random();
        this.prepareWords();
    }

    public String getWord() {
        String result = null;
        while (result == null) {
            if (this.probability > this.random.nextFloat()) {
                result = this.words[this.random.nextInt(words.length)];
            }
        }
        return result;
    }

    private void prepareWords() {
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > WORD_MAX_SIZE) {
                words[i] = words[i].substring(0, WORD_MAX_SIZE);
            }
        }
    }
}
