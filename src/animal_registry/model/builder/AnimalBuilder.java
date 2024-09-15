package animal_registry.model.builder;

import animal_registry.model.animal.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class AnimalBuilder implements Serializable {
    private long currentMaxId = 0; // Отслеживание максимального ID

    // Инициализация текущего максимального ID на основе существующих животных
    public void initializeId(List<Animal> animalList) {
        for (Animal animal : animalList) {
            if (animal.getId() > currentMaxId) {
                currentMaxId = animal.getId();
            }
        }
    }

    // Метод для получения следующего уникального ID
    public long getNextId() {
        return ++currentMaxId;
    }

    // Метод для создания нового животного с уникальным ID
    public Animal build(long id, String name, PetType petType, LocalDate birthDate) {
        switch (petType) {
            case Dog:
                return new Dog(id, name, birthDate);
            case Cat:
                return new Cat(id, name, birthDate);
            case Hamster:
                return new Hamster(id, name, birthDate);
            default:
                throw new IllegalArgumentException("Нет такого вида животного: " + petType);
        }
    }
}
