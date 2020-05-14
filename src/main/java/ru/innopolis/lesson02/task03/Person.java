package ru.innopolis.lesson02.task03;

public class Person {

    private int age;

    private Sex sex;

    private String name;

    public Person(int age, Sex sex, String name) {
        this.setAge(age);
        this.sex = sex;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) throws IllegalArgumentException{
        if (age < 0 || age > 100) {
            throw new IllegalArgumentException("возраст не должен быть больше 100 или меньше 0 " +
                    "Person#setAge");
        }
        this.age = age;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", sex=" + sex +
                ", name='" + name + '\'' +
                '}';
    }
}
