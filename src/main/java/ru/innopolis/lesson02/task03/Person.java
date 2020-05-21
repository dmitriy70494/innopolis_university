package ru.innopolis.lesson02.task03;

import java.util.Objects;

public class Person implements Comparable{

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return getAge() == person.getAge() &&
                getSex() == person.getSex() &&
                Objects.equals(getName(), person.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAge(), getSex(), getName());
    }

    @Override
    public int compareTo(Object o) {
        int result = 0;
        if (this.getClass() != o.getClass()) {
            throw new ClassCastException();
        } else {
            Person person = (Person) o;
            if (this != person && this.getAge() == person.getAge() && this.getName().equals(person.getName())) {
                throw new UserIllegalException("имена людей и возраст совпадают" + this + person);
            }
            result = this.getSex().compareTo(person.getSex());
            if (result == 0) {
                result = Integer.compare(this.getAge(), person.getAge());
                if (result == 0) {
                    result = this.getName().compareTo(person.getName());
                }
            }
        }
        return result;
    }
}
