import animal_registry.model.animal.Animal;
import animal_registry.model.animal.PetType;
import animal_registry.model.animal_registry.AnimalRegistry;
import animal_registry.model.service.Service;
import animal_registry.model.writer.FileHandler;

import java.io.Serializable;
import java.time.LocalDate;

public class Main implements Serializable{
    public static void main(String[] args) {
        Service service = new Service();
        service.addAnimal("Вася", PetType.Cat, LocalDate.of(2012, 8, 10));
        service.addAnimal("Тася", PetType.Dog, LocalDate.of(2019, 10, 11));
        service.addAnimal("Петя", PetType.Hamster, LocalDate.of(2019, 10, 11));
        service.addAnimal("Муся", PetType.Cat, LocalDate.of(2016, 9, 23));
        service.addCommandToAnimal(0, "дай лапу");
        service.addCommandToAnimal(1, "сидеть");
        service.addCommandToAnimal(1, "есть");


//        service.saveRegistry();
//        service.loadRegistry();
        service.getAnimalsByType(PetType.Cat);
        System.out.println(service.getAnimalListInfo());
    }
}