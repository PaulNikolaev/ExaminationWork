package animal_registry.model.service;

import animal_registry.model.animal.Animal;
import animal_registry.model.animal.PetType;
import animal_registry.model.animal_registry.AnimalRegistry;
import animal_registry.model.builder.AnimalBuilder;
import animal_registry.model.writer.FileHandler;

import java.time.LocalDate;
import java.util.List;

public class Service {
    private AnimalRegistry<Animal> animalRegistry;
    private AnimalBuilder animalBuilder;
    private FileHandler fileHandler;

    private void setAnimalRegistry(AnimalRegistry<Animal> animalRegistry) {
        this.animalRegistry = animalRegistry;
    }

    public Service() {
        animalRegistry = new AnimalRegistry<>();
        animalBuilder = new AnimalBuilder();
        this.fileHandler = new FileHandler();
    }

    public void setFileHandler(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    // Добавление животного в реестр
    public Animal addAnimal(String name, PetType petType, LocalDate birthDate) {
        if (isAnimalDuplicate(name, petType, birthDate)) {
            throw new IllegalArgumentException("Животное с таким именем, типом и датой рождения уже существует.");
        }
        Animal animal = animalBuilder.build(name, petType, birthDate);
        animalRegistry.addAnimal(animal);
        return animal;
    }

    // Проверка на наличие животного с одинаковыми именем, типом и датой рождения
    private boolean isAnimalDuplicate(String name, PetType petType, LocalDate birthDate) {
        for (Animal animal : animalRegistry) {
            if (animal.getName().equals(name) &&
                    animal.getPetType() == petType &&
                    animal.getBirthDate().equals(birthDate)) {
                return true;
            }
        }
        return false;
    }

    // Добавление команды животному по его ID
    public String addCommandToAnimal(long id, String command) {
        boolean success = animalRegistry.addCommandToAnimal(id, command);
        return success ? "Команда успешно добавлена животному с ID " + id : "Животное с ID " + id + " не найдено";
    }

    // Получение списка команд животного по его ID
    public String getCommandsByAnimalId(long id) {
        List<String> commands = animalRegistry.getCommandsByAnimalId(id);
        if (commands.isEmpty()) {
            return "У животного с ID " + id + " нет команд или животное не найдено.";
        }
        return "Команды животного с ID " + id + ": " + String.join(", ", commands);
    }

    // Поиск животного по имени
    public String findAnimalById(long id) {
        Animal found = animalRegistry.findAnimalById(id);
        return found != null ? found.toString() : ": животное с ID " + id + " не найден";
    }

    // Поиск животного по имени
    public String findAnimalByName(String name) {
        if (name != null) {
            Animal found = animalRegistry.findAnimalByName(name);
            return found != null ? found.toString() : "Такого животного нет";
        } else {
            return "Имя не может быть null";
        }
    }

    // Удаление животного по ID
    public String removeAnimalById(long id) {
        boolean removed = animalRegistry.removeAnimalById(id);
        return removed ? "Животное с ID " + id + " успешно удалено" : "Животное с ID " + id + " не найдено";
    }

    // Получение списка животных по типу
    public String getAnimalsByType(PetType petType) {
        List<Animal> animals = animalRegistry.getAnimalsByType(petType);
        if (animals.isEmpty()) {
            return "Животных типа " + petType + " нет в реестре.";
        }
        StringBuilder sb = new StringBuilder("Список животных типа " + petType + ":\n");
        for (Animal animal : animals) {
            sb.append(animal).append("\n");
        }
        return sb.toString();
    }

    // Сортировка по имени
    public void sortByName() {
        animalRegistry.sortByName();
    }

    // Получение информации о животных в реестре питомника
    public String getAnimalListInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Реестр питомника: \n");
        for (Animal human : animalRegistry) {
            sb.append(human);
            sb.append("\n");
        }
        return sb.toString();
    }
    // размер реестра
    public int animalRegistrySize() {
        return animalRegistry.animalRegistrySize();
    }

    // Сохранение реестра животных в файл
    public void saveRegistry() {
        fileHandler.save(animalRegistry);
        System.out.println("Реестр животных успешно сохранен.");
    }

    // Загрузка реестра животных из файла
    public void loadRegistry() {
        AnimalRegistry<Animal> loadedRegistry = (AnimalRegistry<Animal>) fileHandler.read();
        if (loadedRegistry != null) {
            animalRegistry = loadedRegistry;
            System.out.println("Реестр животных успешно загружен.");
        } else {
            System.out.println("Ошибка при загрузке реестра.");
        }
    }

}
