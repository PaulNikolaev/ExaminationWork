package animal_registry.model.animal_registry;

import animal_registry.model.animal.PetType;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public interface ItemAnimalRegistry<E> extends Serializable {
    long getId();
    String getName();
    PetType getPetType();
    LocalDate getBirthDate();
    List<String> getCommands();


    void addCommand(String command);
}
