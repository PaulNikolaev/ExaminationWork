package animal_registry.model.animal;

import java.time.LocalDate;
import java.util.List;

public class Dog extends Animal {

    public Dog(long id, String name, LocalDate birthDate, List<String> commands) {
        super(id, name, PetType.Dog, birthDate, commands);
    }

    public Dog(long id, String name, LocalDate birthDate) {
        super(id, name, PetType.Dog, birthDate);
    }

    public Dog(long id, String name) {
        super(id, name, PetType.Dog);
    }

    @Override
    public String getInfo() {
        return "Собака: " + super.getInfo();
    }
}