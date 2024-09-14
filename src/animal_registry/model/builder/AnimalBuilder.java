package animal_registry.model.builder;

import animal_registry.model.animal.*;

import java.io.Serializable;
import java.time.LocalDate;

public class AnimalBuilder implements Serializable {
    private long genId;

    public Animal build (String name, PetType petType, LocalDate birthDate){
        switch (petType) {
            case Dog:
                return new Dog(genId++, name, birthDate);
            case Cat:
                return new Cat(genId++, name, birthDate);
            case Hamster:
                return new Hamster(genId++, name, birthDate);
            default:
                throw new IllegalArgumentException("Нет такого вида животного: " + petType);
        }
    }
}
