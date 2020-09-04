package ru.innopolis.lesson02.task03;
public enum Sex {
    MAN("man"),
    WOMAN("woman");

    private final String sex;

    public String getSex() {
        return sex;
    }

    Sex(final String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return sex;
    }
}
