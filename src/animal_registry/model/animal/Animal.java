package animal_registry.model.animal;

import animal_registry.model.animal_registry.ItemAnimalRegistry;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Animal implements ItemAnimalRegistry<Animal> {
    private long id;
    private String name;
    private PetType petType;
    private LocalDate birthDate;
    private List<String> commands;

    public Animal(long id, String name, PetType petType, LocalDate birthDate, List<String> commands) {
        this.id = id;
        this.name = name;
        this.petType = petType;
        this.birthDate = birthDate;
        this.commands = (commands == null) ? new ArrayList<>() : new ArrayList<>(commands);
    }

    public Animal(long id, String name, PetType petType) {
        this(id, name, petType, null, new ArrayList<>());
    }

    public Animal(long id, String name, PetType petType, List<String> commands) {
        this(id, name, petType, null, commands);
    }

    public Animal(long id, String name, PetType petType, LocalDate birthDate) {
        this(id, name, petType, birthDate, new ArrayList<>());
    }


    public void addCommand(String command) {
        if (command != null && !command.trim().isEmpty()) {
            commands.add(command);
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<String> getCommands() {
        return commands;
    }

    public void setCommands(List<String> commands) {
        this.commands = commands;
    }

    private String formatBirthDate(LocalDate birthDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy 'г.'");
        return birthDate.format(formatter);
    }

    @Override
    public String toString() {
        return getInfo();
    }

    public String getInfo(){
        StringBuilder sb = new StringBuilder();
        sb.append("id: ");
        sb.append(id);
        sb.append(", имя: ");
        sb.append(name);
        sb.append(", вид: ");
        sb.append(getTypeInfo());
        sb.append(", дата рождения: ");
        sb.append(formatBirthDate(birthDate));
        sb.append(", команды: ");
        sb.append(getCommandsInfo());
        return sb.toString();
    }

    private String getTypeInfo(){
        String res = "";
        if (petType.equals(PetType.Cat)) {
            res += "кот";
        } else if (petType.equals(PetType.Dog)) {
            res += "собака";
        } else if (petType.equals(PetType.Hamster)) {
            res += "хомяк";
        }
        return res;
    }

    private String getCommandsInfo() {
        StringBuilder sb = new StringBuilder();
        if (commands != null && !commands.isEmpty()) {
            for (int i = 0; i < commands.size(); i++) {
                sb.append(commands.get(i));
                if (i < commands.size() - 1) {
                    sb.append(", ");
                }
            }
        } else {
            sb.append("нет команд");
        }
        return sb.toString();
    }
}