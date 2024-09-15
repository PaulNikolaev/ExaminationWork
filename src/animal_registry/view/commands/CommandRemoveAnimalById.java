package animal_registry.view.commands;

import animal_registry.view.ConsoleUI;

public class CommandRemoveAnimalById extends Commands {

    public CommandRemoveAnimalById(ConsoleUI consoleUI) {
        super("Удалить животное из реестра по ID.", consoleUI);
    }

    @Override
    public void execute() {
        getConsoleUI().removeAnimalById();
    }
}
