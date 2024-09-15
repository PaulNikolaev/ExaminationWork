package animal_registry.presenter;

import animal_registry.model.animal.PetType;
import animal_registry.model.service.Service;
import animal_registry.view.View;

import java.time.LocalDate;

public class Presenter {
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

    //Добавление команды животному по ID
    public void addCommandToAnimal(long id, String command) {
        service.addCommandToAnimal(id, command);
    }

    // Удаление животного из реестра по ID
    public void removeAnimalById(long id) {
        service.removeAnimalById(id);
    }
}
