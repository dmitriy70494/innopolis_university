package ru.innopolis.lesson06.task2;

import java.util.Random;

public class Paragraph {

    private Sentence sentence;

    /**
     *  в одном абзаце 1<=n3<=20 предложений
     */
    private static final int PARAGRAPH_MAX_SIZE = 20;

    public Paragraph(String[] words, int probability) {
        this.sentence = new Sentence(words, probability);
    }

    public StringBuilder getParagraph() {
        int i = new Random().nextInt(PARAGRAPH_MAX_SIZE) + 1;
        StringBuilder result = new StringBuilder();
        while (i > 0) {
            result.append(this.sentence.getSentence());
            i--;
        }
        return result.append(System.lineSeparator());
    }
}
