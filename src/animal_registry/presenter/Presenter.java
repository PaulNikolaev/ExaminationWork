package animal_registry.presenter;

import animal_registry.model.animal.PetType;
import animal_registry.model.service.Service;
import animal_registry.view.View;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class Presenter implements Serializable {
    private static final long serialVersionUID = 1L;
    private View view;
    private Service service;

    public Presenter(View view) {
        this.view = view;
        service = new Service();
    }

    // Добавление животного в реестр
    public void addAnimal(String name, PetType petType, LocalDate birthdate) {
        service.addAnimal(name, petType, birthdate);
        getAnimalListInfo();
    }

    // Получить информацию о количестве животных в питомнике
    public int animalRegistrySize() {
        return service.animalRegistrySize();
    }

    // Получение информации о всех животных в реестре
    public void getAnimalListInfo() {
        String answer = service.getAnimalListInfo();
        view.printAnswer(answer);
    }

    // Сортировка реестра по имени
    public void sortByName() {
        service.sortByName();
        getAnimalListInfo();
    }

    // Сохранение реестра
    public void saveRegistry() {
        service.saveRegistry();
    }

    // Загрузка реестра
    public void loadRegistry() {
        service.loadRegistry();
    }

    // Метод получения списка животных по виду
    public String getAnimalsByType(PetType petType) {
        return service.getAnimalsByType(petType);
    }

    // Получение списка команд животного по ID
    public List<String> getCommandsByAnimalId(long id) {
        return service.getCommandsByAnimalId(id);
    }
    //Добавление команды животному по ID
    public void addCommandToAnimal(long id, String command) {
        boolean success = service.addCommandToAnimal(id, command);
        if (success) {
            System.out.println("Команда успешно добавлена.");
        } else {
            System.out.println("Ошибка: животное с таким ID не найдено.");
        }
    }

    // Удаление животного из реестра по ID
    public void removeAnimalById(long id) {
        boolean removed = service.removeAnimalById(id);
        if (removed) {
            System.out.println("Животное успешно удалено.");
        } else {
            System.out.println("Ошибка: животное с таким ID не найдено.");
        }
    }

    public boolean checkAnimalExistsById(Long id) {
        boolean checkId = service.checkAnimalExistsById(id);
        return checkId;
    }
}
