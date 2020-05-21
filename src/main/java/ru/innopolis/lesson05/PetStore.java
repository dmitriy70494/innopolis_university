package ru.innopolis.lesson05;

import java.util.*;

import static java.util.Collections.*;

/**
 *
 */
public class PetStore {

    private final Map<String, Set<Integer>> nickname;

    private final Map<Integer, Pet> pets;



    public PetStore() {
        this.pets = new HashMap<>(200);
        this.nickname = new HashMap<>(200);
    }

    /**
     * метод добавления животного в общий список (
     * добавление дубликатов должно приводить к исключительной ситуации)
     *
     * @param pet питомец
     * @return true = питомец добавлен в картотеку
     * @throws UserDoubleException дубль питомца
     */
    public boolean save(Pet pet) throws UserDoubleException {
        if (!this.pets.containsKey(pet.getId())) {
            this.saveNickName(pet);
            return this.pets.put(pet.getId(), pet) != null;
        }
        throw new UserDoubleException("Такой питомец уже есть");
    }

    private void saveNickName(Pet pet) {
        Set<Integer> petNick = nickname.get(pet.getNickname());
        if (petNick == null) {
            petNick = new HashSet<>();
            petNick.add(pet.getId());
            nickname.put(pet.getNickname(), petNick);
        } else {
            petNick.add(pet.getId());
        }
    }

    /**
     * поиск животного по его кличке (поиск эффективный)
     *
     * @param nickname кличка животного
     * @return набор животных с данной кличкой
     */
    public List<Pet> findByNickname(String nickname) {
        List<Pet> result = new ArrayList<>();
        Set<Integer> ids = this.nickname.get(nickname);
        if (ids != null) {
            for (Integer id : ids) {
                result.add(pets.get(id));
            }
        }
        return result;
    }

    /**
     * изменение данных животного
     * @param pet животное
     * @return Pet - если замена произведена, null - если такого элемента по id не найдено
     */
    public Pet update(Integer id, Pet pet) {
        Pet temp = this.pets.replace(id, pet);
        if (temp != null && pet.getNickname() != null && !pet.getNickname().equals(temp.getNickname()) ) {
            this.nickname.get(temp.getNickname()).remove(temp.getId());
            this.saveNickName(pet);
        }
        return temp;
    }

    /**
     * вывод на экран списка животных в отсортированном порядке.
     * Поля для сортировки –  хозяин, кличка животного, вес.
     */
    public void systemOutPets() {
        this.pets.values().stream()
                .sorted()
                .forEach(System.out :: println);
    }
}
