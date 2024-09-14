package animal_registry.model.animal;

import java.time.LocalDate;
import java.util.List;

public class Cat extends Animal {

    public Cat(long id, String name, LocalDate birthDate, List<String> commands) {
        super(id, name, PetType.Cat, birthDate, commands);
    }

    public Cat(long id, String name, LocalDate birthDate) {
        super(id, name, PetType.Cat, birthDate);
    }

    public Cat(long id, String name) {
        super(id, name, PetType.Cat);
    }

    @Override
    public String getInfo() {
        return "Кот: " + super.getInfo();
    }
}