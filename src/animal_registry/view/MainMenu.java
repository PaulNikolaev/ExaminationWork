package animal_registry.view;

import animal_registry.view.commands.*;
import java.util.ArrayList;
import java.util.List;

public class MainMenu {
    List<Commands> commands;

    public MainMenu(ConsoleUI consoleUI) {
        commands = new ArrayList<>();
        commands.add(new CommandLoadRegistry(consoleUI));
        commands.add(new CommandGetAnimalListInfo(consoleUI));
        commands.add(new CommandGetAnimalsByType(consoleUI));
        commands.add(new CommandAddAnimal(consoleUI));
        commands.add(new CommandAddCommandToAnimal(consoleUI));
        commands.add(new CommandRemoveAnimalById(consoleUI));
        commands.add(new CommandAnimalRegistrySize(consoleUI));
//        commands.add(new CommandSortByName(consoleUI));
        commands.add(new CommandFinish(consoleUI));
        commands.add(new CommandFinishAndSave(consoleUI));
    }

    public String menu() {
        StringBuilder sb = new StringBuilder();
        sb.append("Меню:\n");
        for (int i = 0; i < commands.size(); i++) {
            sb.append(i + 1);
            sb.append(". ");
            sb.append(commands.get(i).getDescription());
            sb.append("\n");
        }
        return sb.toString();
    }

    public void  execute(int choice) {
        Commands command = commands.get(choice - 1);
        command.execute();
    }

    public int menuSize() {
        return commands.size();
    }

}
