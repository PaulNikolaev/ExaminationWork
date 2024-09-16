package animal_registry.model.service;

import animal_registry.model.animal.Animal;
import animal_registry.model.animal.PetType;
import animal_registry.model.animal_registry.AnimalRegistry;
import animal_registry.model.builder.AnimalBuilder;
import animal_registry.model.writer.FileHandler;

import java.io.IOException;
import java.io.InvalidClassException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


public class Service implements Serializable {
    private static final long serialVersionUID = 1L;
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
        long newId = animalBuilder.getNextId();
        Animal animal = animalBuilder.build(newId, name, petType, birthDate);
        animalRegistry.addAnimal(animal);
    }


    // Добавление команды животному по его ID
    public boolean addCommandToAnimal(long id, String command) {
        return animalRegistry.addCommandToAnimal(id, command);
    }

    // Получение списка команд животного по его ID
    public List<String> getCommandsByAnimalId(long id) {
        return animalRegistry.getCommandsByAnimalId(id);
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
    public boolean removeAnimalById(long id) {
        return animalRegistry.removeAnimalById(id);
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
        try {
            fileHandler.save(animalRegistry);
            System.out.println("Реестр животных успешно сохранён.");
        } catch (Exception e) {
            System.out.println("Непредвиденная ошибка при сохранении реестра: " + e.getMessage());
        }
    }

    // Загрузка реестра животных из файла
    public void loadRegistry() {
        try {
            AnimalRegistry<Animal> loadedRegistry = (AnimalRegistry<Animal>) fileHandler.read();
            if (loadedRegistry != null) {
                animalRegistry = loadedRegistry;
                animalBuilder.initializeId(animalRegistry.getAnimals());
                System.out.println("Реестр животных успешно загружен.");
            } else {
                throw new Exception("Ошибка: загруженный реестр пуст.");
            }
        } catch (ClassCastException e) {
            System.out.println("Ошибка: несовместимый формат данных реестра.");
        } catch (InvalidClassException e) {
            System.out.println("Ошибка: несовместимая версия класса AnimalRegistry. " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла реестра: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Общая ошибка при загрузке реестра: " + e.getMessage());
        }
    }

    public boolean checkAnimalExistsById(Long id) {
        boolean checkId = animalRegistry.checkAnimalExistsById(id);
        return checkId;
    }
}
