package ru.innopolis.lesson05;

import ru.innopolis.lesson02.task03.*;

import java.util.Objects;

public class Pet implements Comparable{

    private int id;

    private String nickname;

    private Person person;

    private float weight;

    public Pet(int id, String nickname, Person person, float weight) {
        this.id = id;
        this.nickname = nickname;
        this.person = person;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", person=" + person +
                ", weight=" + weight +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pet)) return false;
        Pet pet = (Pet) o;
        return getId() == pet.getId() &&
                Float.compare(pet.getWeight(), getWeight()) == 0 &&
                getNickname().equals(pet.getNickname()) &&
                getPerson().equals(pet.getPerson());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNickname(), getPerson(), getWeight());
    }

    @Override
    public int compareTo(Object o) {
        int result = 0;
        if (this.getClass() != o.getClass()) {
            throw new ClassCastException();
        } else {
            Pet pet = (Pet) o;
            result = this.getPerson().compareTo(pet.getPerson());
            if (result == 0) {
                result = this.getNickname().compareTo(pet.getNickname());
                if (result == 0) {
                    result = Float.compare(this.getWeight(), pet.getWeight());
                }
            }
        }
        return result;
    }
}
