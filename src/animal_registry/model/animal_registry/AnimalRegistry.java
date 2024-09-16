package animal_registry.model.animal_registry;

import animal_registry.model.animal.PetType;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
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

    // Добавление животного в реестр с проверкой на дубликаты
    public void addAnimal(E animalToAdd) {
        if (isAnimalDuplicate(animalToAdd.getName(), animalToAdd.getPetType(), animalToAdd.getBirthDate())) {
            throw new IllegalArgumentException("Животное с таким именем, типом и датой рождения уже существует.");
        }
        this.animal.add(animalToAdd);
    }

    // Проверка на наличие животного с одинаковыми именем, типом и датой рождения
    private boolean isAnimalDuplicate(String name, PetType petType, LocalDate birthDate) {
        for (E animal : this.animal) {
            if (animal.getName().equals(name) &&
                    animal.getPetType() == petType &&
                    animal.getBirthDate().equals(birthDate)) {
                return true;
            }
        }
        return false;
    }

    // Добавление команды животному по его ID
    public boolean addCommandToAnimal(long id, String command) {
        E foundAnimal = findAnimalByIdInternal(id);
        if (foundAnimal != null) {
            foundAnimal.addCommand(command);
            return true;
        }
        return false;
    }

    // Получение списка команд животного по его ID
    public void getCommandsByAnimalId(long id) {
        E foundAnimal = findAnimalByIdInternal(id);  // Используем приватный метод для поиска
        if (foundAnimal != null) {
            List<String> commands = foundAnimal.getCommands();
            if (commands.isEmpty()) {
                System.out.println("У животного с ID " + id + " нет команд.");
            } else {
                System.out.println("Команды животного с ID " + id + ": " + String.join(", ", commands));
            }
        } else {
            System.out.println("Животное с ID " + id + " не найдено.");
        }
    }

    // Поиск животного по ID
    public void findAnimalById(long id) {
        E foundAnimal = findAnimalByIdInternal(id);
        if (foundAnimal != null) {
            System.out.println(foundAnimal);
        } else {
            System.out.println("Животное с ID " + id + " не найдено.");
        }
    }

    // Поиск животного по имени
    public void findAnimalByName(String name) {
        E foundAnimal = findAnimalByNameInternal(name);  // Используем приватный метод для поиска
        if (foundAnimal != null) {
            System.out.println(foundAnimal);
        } else {
            System.out.println("Животное с именем " + name + " не найдено.");
        }
    }

    // Приватный метод для поиска по ID
    private E findAnimalByIdInternal(long id) {
        for (E animal : this.animal) {
            if (animal.getId() == id) {
                return animal;
            }
        }
        return null;
    }

    // Приватный метод для поиска по имени
    private E findAnimalByNameInternal(String name) {
        for (E animal : this.animal) {
            if (animal.getName().equals(name)) {
                return animal;
            }
        }
        return null;
    }

    // Удаление животного по ID
    public boolean removeAnimalById(long id) {
        E animalToRemove = findAnimalByIdInternal(id);
        if (animalToRemove != null) {
            animal.remove(animalToRemove);
            return true;
        }
        return false;
    }

    // Метод для получения информации о всех животных
    public String getAnimalListInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Реестр питомника: \n");
        for (E animal : this.animal) {
            sb.append(animal);
            sb.append("\n");
        }
        return sb.toString();
    }

    // Метод для получения списка животных по типу
    public String getAnimalsByType(PetType petType) {
        List<E> animalsByType = new ArrayList<>();
        for (E animal : animal) {
            if (animal.getPetType() == petType) {
                animalsByType.add(animal);
            }
        }

        String russianPetType = getRussianPetType(petType);

        if (animalsByType.isEmpty()) {
            return "Животных типа " + russianPetType + " нет в реестре.";
        }

        StringBuilder sb = new StringBuilder("Список животных типа \"" + russianPetType + "\":\n");
        for (E animal : animalsByType) {
            sb.append(animal).append("\n");
        }
        return sb.toString();
    }

    private String getRussianPetType(PetType petType) {
        switch (petType) {
            case Cat: return "кот";
            case Dog: return "собака";
            case Hamster: return "хомяк";
            default: return "неизвестный вид";
        }
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

    // Сортировка по имени
    public void sortByName() {
        animal.sort(new AnimalComparatorByName<>());
    }

    // Размер реестра
    public int animalRegistrySize() {
        return animal.size();
    }

    @Override
    public String toString() {
        return getInfo();
    }

    public List<E> getAnimals() {
        return animal;
    }
    @Override
    public Iterator<E> iterator() {
        return animal.iterator();
    }

    public boolean checkAnimalExistsById(Long id) {
        E foundAnimal = findAnimalByIdInternal(id);
        return foundAnimal != null;
    }
}
