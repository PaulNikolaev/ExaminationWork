package animal_registry.model.animal_registry;

import animal_registry.model.animal.PetType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class AnimalRegistry<E extends ItemAnimalRegistry<E>> implements Serializable, Iterable<E> {
    private final List<E> animal;

    public AnimalRegistry() {
        this(new ArrayList<>());
    }

    public AnimalRegistry(List<E> animalList) {
        this.animal = animalList;
    }

//    Добавление животного в реестр
    public void addAnimal(E animal) {
        this.animal.add(animal);
    }

    // Добавление команды животному по ID
    public boolean addCommandToAnimal(long id, String command) {
        E animal = findAnimalById(id);
        if (animal != null) {
            animal.addCommand(command);
            return true;
        }
        return false;
    }

    // Получение списка команд животного по ID
    public List<String> getCommandsByAnimalId(long id) {
        E animal = findAnimalById(id);
        if (animal != null) {
            return animal.getCommands(); // Возвращаем команды животного
        }
        return Collections.emptyList(); // Животное с таким ID не найдено
    }

//    Поиск животного по имени
    public E findAnimalByName(String name) {
        for (E animal: animal){
            if (animal.getName().equals(name)){
                return animal;
            }
        }
        return null;
    }


// Поиск животного по ID
    public E findAnimalById(long id) {
        for (E animal: animal) {
            if (animal.getId() == id) {
                return animal;
            }
        }
        return null;
    }

    // Удаление животного по ID
    public boolean removeAnimalById(long id) {
        Iterator<E> iterator = animal.iterator();
        while (iterator.hasNext()) {
            E currentAnimal = iterator.next();
            if (currentAnimal.getId() == id) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    // Метод для получения списка животных по типу
    public List<E> getAnimalsByType(PetType petType) {
        List<E> animalsByType = new ArrayList<>();
        for (E animal : animal) {
            if (animal.getPetType() == petType) {
                animalsByType.add(animal);
            }
        }
        return animalsByType;
    }

    @Override
    public String toString() {
        return getInfo();
    }


    private String getInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("В питомнике ");
        sb.append(animal.size());
        sb.append(" животных: \n");
        for (E animal: animal){
            sb.append(animal);
            sb.append("\n");
        }
        return sb.toString();
    }


    @Override
    public Iterator<E> iterator() {
        return new AnimalIterator<>(animal);
    }


    public void sortByName() {
        animal.sort(new AnimalComparatorByName<>());
    }

    public int animalRegistrySize() {
        return animal.size();
    }
}
