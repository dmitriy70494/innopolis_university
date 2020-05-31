package ru.innopolis.lesson06.task2;

import java.util.List;
import java.util.Random;

public class Sentence {

    /**
     * Предложение состоит из 1<=n1<=15 слов
     */
    private static final int SENTENCE_MAX_SIZE = 15;

    /**
     * Предложение заканчивается (.|!|?)+" "
     */
    private static final String[] SYMBOL_OF_END_SENTENCE = {".", "!", "?"};

    /**
     * Предложение заканчивается (.|!|?)+" "
     */
    private static final int MAX_COUNT_SYMBOL_OF_END_SENTENCE = 3;

    private Word word;

    private Random random;

    public Sentence(String[] words, int probability) {
        this.word = new Word(words, probability);
        this.random = new Random();
    }

    public StringBuilder getSentence() {
        StringBuilder result = new StringBuilder(this.word.getWord());
        result.replace(0, 1, result.substring(0, 1).toUpperCase());
        this.random.ints(this.random.nextInt(SENTENCE_MAX_SIZE), 1, 10)
                .forEach(i -> result.append(getComma(i)).append(this.word.getWord()));
        return result.append(this.genEndOfSentence());
    }

    /*
     * Генерирует окончание предложения, согласно паттерну (.|!|?)+" "
     */
    private StringBuilder genEndOfSentence() {
        StringBuilder result = new StringBuilder();
        this.random.ints(this.random.nextInt(MAX_COUNT_SYMBOL_OF_END_SENTENCE),
                0, SYMBOL_OF_END_SENTENCE.length)
                .forEach(i -> result.append(SYMBOL_OF_END_SENTENCE[i]));
        return result.append(" ");
    }

    private String getComma(int i) {
        return i > 8 ? ", " : " ";
    }
}
