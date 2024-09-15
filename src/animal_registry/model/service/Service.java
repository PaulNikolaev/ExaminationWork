package animal_registry.model.service;

import animal_registry.model.animal.Animal;
import animal_registry.model.animal.PetType;
import animal_registry.model.animal_registry.AnimalRegistry;
import animal_registry.model.builder.AnimalBuilder;
import animal_registry.model.writer.FileHandler;
import java.time.LocalDate;


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
    public void addAnimal(String name, PetType petType, LocalDate birthDate) {
        Animal animal = animalBuilder.build(name, petType, birthDate);
        animalRegistry.addAnimal(animal);
    }


    // Добавление команды животному по его ID
    public void addCommandToAnimal(long id, String command) {
        animalRegistry.addCommandToAnimal(id, command);
    }

    // Получение списка команд животного по его ID
    public void getCommandsByAnimalId(long id) {
        animalRegistry.getCommandsByAnimalId(id);
    }

    // Поиск животного по ID
    public void findAnimalById(long id) {
        animalRegistry.findAnimalById(id);
    }

    // Поиск животного по имени
    public void findAnimalByName(String name) {
        animalRegistry.findAnimalByName(name);
    }

    // Удаление животного по его ID
    public void removeAnimalById(long id) {
        animalRegistry.removeAnimalById(id); // Просто вызываем метод удаления из реестра
    }

    // Сортировка по имени
    public void sortByName() {
        animalRegistry.sortByName();
    }

    // Получение списка животных по типу
    public String getAnimalsByType(PetType petType) {
        return animalRegistry.getAnimalsByType(petType);
    }

    // Получение информации о всех животных в реестре
    public String getAnimalListInfo() {
        return animalRegistry.getAnimalListInfo();     }

    // Размер реестра
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
