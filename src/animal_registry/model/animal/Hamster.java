package animal_registry.model.animal;

import java.time.LocalDate;
import java.util.List;

public class Hamster extends Animal {

    public Hamster(long id, String name, LocalDate birthDate, List<String> commands) {
        super(id, name, PetType.Hamster, birthDate, commands);
    }

    public Hamster(long id, String name, LocalDate birthDate) {
        super(id, name, PetType.Hamster, birthDate);
    }

    public Hamster(long id, String name) {
        super(id, name, PetType.Hamster);
    }

    @Override
    public String getInfo() {
        return super.getInfo();
    }
}