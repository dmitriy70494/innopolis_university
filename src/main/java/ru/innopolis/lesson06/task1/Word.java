package ru.innopolis.lesson06.task1;

import java.util.Objects;

public class Word implements Comparable {

    private String word;

    public Word(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Word)) return false;
        Word word1 = (Word) o;
        return getWord().equalsIgnoreCase(word1.getWord());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWord().toLowerCase());
    }

    @Override
    public int compareTo(Object o) {
        if (o != null && this.getClass() != o.getClass()) {
            throw new ClassCastException();
        } else {
            if (o != null) {
                Word word = (Word) o;
                return getWord().compareToIgnoreCase(word.getWord());
            } else {
                return 1;
            }
        }
    }
}
